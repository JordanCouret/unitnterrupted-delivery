FROM openjdk:11-slim
ADD target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "exec java -server -jar /app.jar" ]
