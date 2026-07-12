# Java AI ChatBot 🤖

A desktop chatbot application built with **Java Swing** for the GUI and powered by the **Anthropic Claude API** for real, intelligent conversation.

![Java](https://img.shields.io/badge/Java-11%2B-orange)
![Swing](https://img.shields.io/badge/GUI-Swing-blue)
![Claude API](https://img.shields.io/badge/AI-Claude%20API-purple)
![License](https://img.shields.io/badge/License-MIT-green)

---

## 📖 Overview

This project is a fully functional chatbot desktop application. Unlike simple rule-based bots, this app sends every user message to the **Claude API**, so responses are genuinely context-aware and intelligent — not just keyword matching.

The app is built using clean, separated architecture:

- `ChatMessage` → Data model for a single message
- `ChatEngine` → Handles all AI/API logic
- `ChatBotGUI` → Swing-based user interface
- `ChatBotApp` → Application entry point

---

## ✨ Features

- 💬 Real AI-powered responses via the Claude API
- 🧠 Conversation memory — the bot remembers earlier messages in the session
- ⚡ Non-blocking UI using `SwingWorker` (app never freezes while waiting for a reply)
- 🎨 Clean, styled chat bubbles (user vs. bot)
- 🔐 API key handled securely via environment variable (never hardcoded)
- ⌨️ Send messages with the **Enter** key or the Send button

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java 11+ |
| GUI Framework | Java Swing |
| AI Backend | Anthropic Claude API |
| Networking | `java.net.http.HttpClient` (built-in, no external libraries) |

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 11 or higher installed
- An Anthropic API key ([get one here](https://console.anthropic.com))

### 1. Clone the repository
```bash
git clone https://github.com/your-username/java-ai-chatbot.git
cd java-ai-chatbot
```

### 2. Set your API key as an environment variable

**Windows (PowerShell):**
```powershell
[System.Environment]::SetEnvironmentVariable("ANTHROPIC_API_KEY", "your-api-key-here", "User")
```

**Mac/Linux:**
```bash
export ANTHROPIC_API_KEY="your-api-key-here"
```

### 3. Compile and run
```bash
javac ChatBotApp.java
java ChatBotApp
```

---

## 📂 Project Structure

```
java-ai-chatbot/
│
├── ChatBotApp.java     # Main application file (all classes)
├── README.md           # Project documentation
└── LICENSE             # License file
```

---

## 🔒 Security Note

This project reads the API key from an environment variable rather than storing it in source code. **Never commit your API key to GitHub.** If you fork this project, always use your own key via environment variables.

---

## 👩‍💻 Author

**Sawera Fareed**
Software Engineering student, building projects in Java, AI integration, and full-stack development.

- GitHub: [your-github-link]
- Portfolio: [your-portfolio-link]

---

## 📄 License

This project is licensed under the MIT License — feel free to use and modify it.
