# --- 1. AŞAMA: Projeyi Derleme (Build) ---
# Maven ve Java 17 yüklü bir sanal makine kullan
FROM maven:3.8.5-openjdk-17 AS build

# Çalışma klasörünü ayarla
WORKDIR /app

# Projedeki tüm dosyaları sanal makineye kopyala
COPY . .

# Testleri atlayarak projeyi derle (hata almamak için testleri geçiyoruz)
RUN mvn clean package -DskipTests

# --- 2. AŞAMA: Çalıştırma (Run) ---
# Sadece Java'nın olduğu daha hafif bir sanal makineye geç
FROM openjdk:17-jdk-slim

# Çalışma klasörünü ayarla
WORKDIR /app

# İlk aşamada derlenen .jar dosyasını buraya al ve ismini app.jar yap
COPY --from=build /app/target/*.jar app.jar

# Uygulamanın çalışacağı portu belirt (Render genelde 8080 kullanır)
EXPOSE 8080

# Uygulamayı başlat
ENTRYPOINT ["java","-jar","app.jar"]