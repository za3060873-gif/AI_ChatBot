import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;
public class ChatBotApp {
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread (Swing best practice)
        SwingUtilities.invokeLater(() -> {
            ChatBotGUI gui = new ChatBotGUI();
            gui.setVisible(true);
        });
    }
}

class ChatMessage {
    enum Sender { USER, BOT }

    private final Sender sender;
    private final String text;

    public ChatMessage(Sender sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public Sender getSender() { return sender; }
    public String getText() { return text; }
}

class ChatEngine {

    // Keyword -> possible responses (randomized for variety)
    private final Map<String, List<String>> knowledgeBase = new LinkedHashMap<>();
    private final Random random = new Random();
    private String userName = null;

    public ChatEngine() {
        initKnowledgeBase();
    }

    private void initKnowledgeBase() {
        knowledgeBase.put("hello", List.of(
                "Hi there! How can I help you today?",
                "Hello! What's on your mind?"
        ));
        knowledgeBase.put("hi", List.of(
                "Hey! Nice to see you.",
                "Hi! How are you doing?"
        ));
        knowledgeBase.put("how are you", List.of(
                "I'm just code, but I'm running smoothly! How about you?"
        ));
        knowledgeBase.put("name", List.of(
                "I'm ChatBot, your Java-powered assistant."
        ));
        knowledgeBase.put("time", List.of(
                "I can't check the real clock right now, but you can see it in your system tray!"
        ));
        knowledgeBase.put("java", List.of(
                "Java is a great choice for building GUI apps like this one, using Swing!"
        ));
        knowledgeBase.put("bye", List.of(
                "Goodbye! Have a great day!",
                "See you later!"
        ));
        knowledgeBase.put("thanks", List.of(
                "You're welcome!",
                "Anytime! Happy to help."
        ));
        knowledgeBase.put("help", List.of(
                "You can ask me about my name, how I work, or just chat casually!"
        ));
    }


    public String generateResponse(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "Please type something so I can respond!";
        }

        String input = userInput.toLowerCase().trim();

        // Capture user's name if they introduce themselves
        if (input.startsWith("my name is")) {
            userName = capitalize(input.replace("my name is", "").trim());
            return "Nice to meet you, " + userName + "!";
        }

        if (input.contains("your name") && userName != null) {
            return "You told me your name is " + userName + ". As for me, I'm ChatBot!";
        }

        // Keyword matching against knowledge base
        for (Map.Entry<String, List<String>> entry : knowledgeBase.entrySet()) {
            if (input.contains(entry.getKey())) {
                List<String> options = entry.getValue();
                return options.get(random.nextInt(options.size()));
            }
        }

        // Fallback response
        return "I'm not sure I understand. Could you rephrase that?";
    }

    private String capitalize(String s) {
        if (s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}

class ChatBotGUI extends JFrame {

    private final ChatEngine engine;
    private final JTextPane chatArea;
    private final StyledDocument document;
    private final JTextField inputField;
    private final JButton sendButton;

    // Colors for chat bubbles
    private static final Color USER_COLOR = new Color(0, 102, 204);
    private static final Color BOT_COLOR = new Color(0, 128, 0);
    private static final Color BG_COLOR = new Color(245, 245, 250);

    public ChatBotGUI() {
        this.engine = new ChatEngine();

        // ---- Frame setup ----
        setTitle("Java AI ChatBot");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---- Chat display area ----
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setBackground(BG_COLOR);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        document = chatArea.getStyledDocument();

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // ---- Input panel (bottom) ----
        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));

        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        sendButton.setBackground(new Color(0, 102, 204));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // ---- Event handling ----
        sendButton.addActionListener(this::handleSend);
        inputField.addActionListener(this::handleSend); // Enter key also sends

        // Greet the user on startup
        appendMessage(new ChatMessage(ChatMessage.Sender.BOT,
                "Hello! I'm your Java ChatBot. Ask me anything!"));
    }

    private void handleSend(ActionEvent e) {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        appendMessage(new ChatMessage(ChatMessage.Sender.USER, userText));
        inputField.setText("");

        // Simulate slight "thinking" delay for realism (non-blocking)
        Timer timer = new Timer(400, evt -> {
            String botReply = engine.generateResponse(userText);
            appendMessage(new ChatMessage(ChatMessage.Sender.BOT, botReply));
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void appendMessage(ChatMessage message) {
        try {
            Style style = chatArea.addStyle("Style", null);
            boolean isUser = message.getSender() == ChatMessage.Sender.USER;

            StyleConstants.setForeground(style, isUser ? USER_COLOR : BOT_COLOR);
            StyleConstants.setBold(style, true);
            String label = isUser ? "You: " : "Bot: ";
            document.insertString(document.getLength(), label, style);

            Style bodyStyle = chatArea.addStyle("Body", null);
            StyleConstants.setForeground(bodyStyle, Color.BLACK);
            StyleConstants.setBold(bodyStyle, false);
            document.insertString(document.getLength(), message.getText() + "\n\n", bodyStyle);

            // Auto-scroll to bottom
            chatArea.setCaretPosition(document.getLength());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}