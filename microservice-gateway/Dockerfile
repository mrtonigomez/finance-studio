# Dockerfile base para Spring Boot con Maven
FROM openjdk:17-jdk-slim

# Argumento para pasar JAR desde build
ARG JAR_FILE=target/*.jar

# Copiar JAR al contenedor
COPY ${JAR_FILE} app.jar

# Copiar wait-for-it.sh
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Exponer puerto
EXPOSE 8080

# Comando para ejecutar
ENTRYPOINT ["java","-jar","/app.jar"]
