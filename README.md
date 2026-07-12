# Java ChatBot (Rule-Based) 🤖

A simple desktop chatbot built with **Java Swing**. This version works **fully offline** — no API key or internet connection required. It uses keyword matching to generate responses.

![Java](https://img.shields.io/badge/Java-11%2B-orange)
![Swing](https://img.shields.io/badge/GUI-Swing-blue)
![Offline](https://img.shields.io/badge/Mode-Offline-lightgrey)

---

## 📖 Overview

This project is a beginner-friendly, rule-based chatbot desktop application. It doesn't connect to any external AI service — instead, it matches keywords in the user's message against a predefined knowledge base and returns a relevant response.

The app follows a clean, separated architecture:

- `ChatMessage` → Data model for a single message
- `ChatEngine` → Keyword-matching logic and response generation
- `ChatBotGUI` → Swing-based user interface
- `ChatBotApp` → Application entry point

---

## ✨ Features

- 💬 Keyword-based response matching (hello, name, time, java, bye, thanks, help, etc.)
- 🧠 Remembers the user's name within the session (if they say "my name is...")
- ⏱️ Simulated "thinking" delay using `javax.swing.Timer` for a more natural feel
- 🎨 Styled chat bubbles — blue for user, green for bot
- ⌨️ Send messages with the **Enter** key or the Send button
- 📴 Works completely offline — no API key, no internet needed

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java 11+ |
| GUI Framework | Java Swing |
| Logic | Rule-based keyword matching (`HashMap`) |

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 11 or higher installed

### 1. Clone the repository
```bash
git clone https://github.com/your-username/java-chatbot.git
cd java-chatbot
```

### 2. Compile and run
```bash
javac ChatBotApp.java
java ChatBotApp
```

That's it — no setup, no API keys, no environment variables needed.

---

## 📂 Project Structure

```
java-chatbot/
│
├── ChatBotApp.java     # Main application file (all classes)
├── README.md           # Project documentation
└── LICENSE             # License file
```

---

## ⚠️ Limitations

Since this is a rule-based bot (not real AI), it can only respond to messages that contain one of its known keywords. Open-ended or unexpected questions will get a generic fallback reply: *"I'm not sure I understand. Could you rephrase that?"*

---

## 👩‍💻 Author

**Sawera Fareed**
Software Engineering student, building projects in Java and GUI development.



## 📄 License

This project is licensed under the MIT License — feel free to use and modify it.
