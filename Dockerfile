FROM openjdk:21-slim
EXPOSE 8088
COPY ./build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]