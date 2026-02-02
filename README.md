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

#### 3. Open in Browser
Visit: `http://localhost:8080`

### ☁️ Deployment (Render)
This application is optimized for Render. It uses the `Dockerfile` in the root directory to build the image.

1.  Connect your GitHub repo to Render.
2.  Select **"Docker"** as the Environment.
3.  Render will automatically build and deploy the app.

---

<a name="-türkçe"></a>
## 🇹🇷 Türkçe

### 🎯 Proje Hakkında

**Video2Quiz**, eğitim videolarını "pasif izleme" eylemini "aktif öğrenme"ye dönüştürmek amacıyla geliştirilmiş yapay zeka destekli bir araçtır. Kullanıcı bir video linki (örneğin YouTube) girdiğinde, uygulama arka planda içeriği analiz eder ve izleyicinin konuyu anlayıp anlamadığını ölçen çoktan seçmeli soruları otomatik olarak üretir.

Bu proje, frontend ve backend'in tek bir çatı altında toplandığı **Monolitik** bir mimariye sahiptir ve şu anda **Render** üzerinde canlı olarak çalışmaktadır.

🚀 **Canlı Demo:** [video2quiz.engrare.com](https://video2quiz.engrare.com/)

### ✨ Özellikler

* **🔍 Akıllı İçerik Analizi:** Video içeriğini tarar ve bağlama uygun sorular çıkarır.
* **⚡ Tak-Çalıştır (Plug & Play):** Frontend, Spring Boot içerisine gömülüdür. Ayrı bir kurulum gerektirmez.
* **☁️ Bulut Tabanlı:** Render altyapısı üzerinde Docker konteynerleri ile çalışır.
* **📱 Responsive Arayüz:** Mobil ve masaüstü cihazlarla tam uyumlu modern tasarım.

### 🛠 Teknoloji Yığını

| Alan | Teknoloji | Açıklama |
| :--- | :--- | :--- |
| **Backend** | Java 17+, Spring Boot | Ana uygulama çatısı ve REST API |
| **Frontend** | HTML5, CSS3, JS | Thymeleaf/Static Resources ile sunulur |
| **Sunucu** | Render | Cloud Platform (PaaS) |
| **Dağıtım** | Docker | Konteynerizasyon |

### 🚀 Kurulum ve Çalıştırma

Bu projeyi çalıştırmak için bilgisayarınızda sadece **Java (JDK 17 veya üzeri)** yüklü olması yeterlidir. Node.js veya npm kurulumuna **gerek yoktur.**

#### 1. Projeyi Klonlayın
```bash
git clone [https://github.com/SweetRomeo/Engrare-Interview-App.git](https://github.com/SweetRomeo/Engrare-Interview-App.git)
cd Engrare-Interview-App



