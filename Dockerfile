FROM maven:3.8.6-openjdk-17-slim
WORKDIR /app
ARG JAR_FILE=target/*.jar
RUN mvn clean package
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
