FROM eclipse-temurin:21-jdk-jammy
COPY target/curtomers-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]