# 🎥 Video2Quiz

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Render](https://img.shields.io/badge/Render-Deployed-46E3B7?style=for-the-badge&logo=render&logoColor=white)](https://render.com/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

**Turn video content into interactive quizzes in seconds.** *Video içeriklerini saniyeler içinde interaktif sınavlara dönüştürün.*

🌍 **Language / Dil**
[🇺🇸 English](#-english) | [🇹🇷 Türkçe](#-türkçe)

---

<a name="-english"></a>
## 🇺🇸 English

### 🎯 About the Project

**Video2Quiz** is an AI-powered tool designed to transform passive video watching into active learning. By simply entering a video link (e.g., YouTube), the application analyzes the content in the background and automatically generates relevant multiple-choice questions to test the viewer's understanding.

This project features a **Monolithic** architecture where the frontend and backend are unified. The application is containerized with Docker and currently deployed on **Render**.

🚀 **Live Demo:** [video2quiz.engrare.com](https://video2quiz.engrare.com/)

### ✨ Key Features

* **🔍 Smart Content Analysis:** Scans video content to generate context-aware questions.
* **⚡ Plug & Play:** The frontend is embedded within Spring Boot. No separate frontend server needed.
* **☁️ Cloud Native:** Deployed and hosted on Render using Docker containers.
* **📱 Responsive UI:** Modern design compatible with both mobile and desktop.

### 🛠 Tech Stack

| Area | Technology | Description |
| :--- | :--- | :--- |
| **Backend** | Java 17+, Spring Boot | Main framework & REST API |
| **Frontend** | HTML5, CSS3, JS | Served via Thymeleaf/Static Resources |
| **Hosting** | Render | Cloud Platform (PaaS) |
| **Deployment** | Docker | Containerization |

### 🚀 Installation & Run

You only need **Java (JDK 17+)** installed on your machine. **Node.js or npm is NOT required.**

#### 1. Clone the Repository
```bash
git clone [https://github.com/SweetRomeo/Engrare-Interview-App.git](https://github.com/SweetRomeo/Engrare-Interview-App.git)
cd Engrare-Interview-App
```
#### 2. Start the App (Local)
The easiest way is to use your favorite IDE (IntelliJ IDEA, Eclipse) or Terminal:

**Mac/Linux:**
```bash
./mvnw spring-boot:run
```

**Windows:**
```dos
mvnw spring-boot:run
```



