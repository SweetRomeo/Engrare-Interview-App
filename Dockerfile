# --- 1. AŞAMA: Java 21 ve Maven ile Derleme ---
# Maven 3.9 ve Java 21 (Eclipse Temurin) yüklü imaj
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

# Testleri atlayarak derle
RUN mvn clean package -DskipTests

# --- 2. AŞAMA: Çalıştırma ---
# Uygulamayı çalıştırmak için Java 21 (Eclipse Temurin)
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Derlenen dosyayı kopyala
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]