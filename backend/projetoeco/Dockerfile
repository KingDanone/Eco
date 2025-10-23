# Estágio 1: Build da aplicação com Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Criação da imagem final
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia o JAR da aplicação do estágio de build
COPY --from=build /app/target/projetoeco-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
