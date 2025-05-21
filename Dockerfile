FROM maven:latest
WORKDIR /app
# ARG JAR_FILE=./target/*.jar
COPY . .
RUN mvn clean package
COPY ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
