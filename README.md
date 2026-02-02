# 🎥 Video2Quiz

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

**Video içeriklerini saniyeler içinde interaktif sınavlara dönüştürün.**

Video2Quiz, eğitim videolarını veya herhangi bir video içeriğini analiz ederek, izleyicinin konuyu anlayıp anlamadığını ölçen otomatik testler (quiz) üreten yapay zeka destekli bir araçtır.

🚀 **Canlı Demo:** [video2quiz.engrare.com](https://video2quiz.engrare.com/)

---

## 📖 İçindekiler
- [Proje Hakkında](#-proje-hakkında)
- [Özellikler](#-özellikler)
- [Teknoloji Yığını](#-teknoloji-yığını)
- [Kurulum ve Çalıştırma](#-kurulum-ve-çalıştırma)
- [Proje Yapısı](#-proje-yapısı)
- [Katkıda Bulunma](#-katkıda-bulunma)

---

## 🎯 Proje Hakkında

Eğitim süreçlerinde "pasif izleme" eylemini "aktif öğrenme"ye dönüştürmek amacıyla geliştirilmiştir. Kullanıcı bir video linki (örneğin YouTube) girdiğinde, Video2Quiz arka planda içeriği işler ve ilgili çoktan seçmeli sorular üretir.

Bu proje, frontend ve backend'in tek bir çatı altında toplandığı **Monolitik** bir mimariye sahiptir. Bu sayede karmaşık kurulum süreçlerine (npm install, webpack vb.) gerek kalmadan tek tıkla çalışır.

---

## ✨ Özellikler

* **🔍 Akıllı İçerik Analizi:** Video içeriğini tarar ve bağlama uygun sorular çıkarır.
* **⚡ Tak-Çalıştır (Plug & Play):** Frontend, Spring Boot içerisine gömülüdür. Ayrı bir frontend sunucusu başlatmanız gerekmez.
* **📱 Responsive Arayüz:** Mobil ve masaüstü cihazlarla tam uyumlu modern tasarım.
* **🐳 Docker Ready:** Tek komutla konteynerize edilebilir ve buluta deploy edilebilir.
* **RESTful API:** Arka plan servisleri genişletilebilir API yapısında kurgulanmıştır.

---

## 🛠 Teknoloji Yığını

| Alan | Teknoloji | Açıklama |
| :--- | :--- | :--- |
| **Backend** | Java 17+, Spring Boot | Ana uygulama çatısı ve REST API |
| **Frontend** | HTML5, CSS3, JS | Thymeleaf/Static Resources ile sunulur |
| **Build Tool** | Maven | Bağımlılık yönetimi ve derleme |
| **Deployment** | Docker | Konteynerizasyon |

---

## 🚀 Kurulum ve Çalıştırma

Bu projeyi çalıştırmak için bilgisayarınızda sadece **Java (JDK 17 veya üzeri)** yüklü olması yeterlidir. Node.js veya npm kurulumuna **gerek yoktur.**

### 1. Projeyi Klonlayın
```bash
git clone [https://github.com/SweetRomeo/Engrare-Interview-App.git](https://github.com/SweetRomeo/Engrare-Interview-App.git)
cd Engrare-Interview-App
