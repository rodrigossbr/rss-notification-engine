# Estágio 1: Build (Compilação)
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# Copia os arquivos do Maven para aproveitar o cache de camadas
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copia o código fonte e gera o JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Estágio 2: Runtime (Execução)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Cria um usuário não-root por segurança
RUN addgroup -S rssgroup && adduser -S rssuser -G rssgroup
USER rssuser

# Copia apenas o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Configurações de JVM para Cloud/Containers
ENTRYPOINT ["java", \
            "-XX:+UseZGC", \
            "-XX:MaxRAMPercentage=75.0", \
            "-Dspring.profiles.active=prod", \
            "-jar", "app.jar"]

EXPOSE 8080