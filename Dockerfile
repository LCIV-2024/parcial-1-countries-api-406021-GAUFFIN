FROM openjdk:17-jdk-alpine
COPY ./target/*.jar lcii-scaffolding.jar
ENTRYPOINT ["java","-jar","lcii-scaffolding.jar"]