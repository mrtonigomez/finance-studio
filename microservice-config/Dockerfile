# Dockerfile base para Spring Boot con Maven
FROM openjdk:17-jdk-slim

# Argumento para pasar JAR desde build
ARG JAR_FILE=target/*.jar

# Copiar JAR al contenedor
COPY ${JAR_FILE} app.jar

# Exponer puerto
EXPOSE 8888

# Comando para ejecutar
ENTRYPOINT ["java","-jar","/app.jar"]
