#FROM maven:3.8.4-jdk-11-slim AS build
#
#COPY src /app/src
#COPY pom.xml /app
#
#WORKDIR /app
#RUN mvn clean install
#
#FROM openjdk:11-jre-slim
#
#COPY --from=build /app/target/authuser-0.0.1-SNAPSHOT.jar /app/app.jar
#
#WORKDIR /app
#
#EXPOSE 8087
#
## Comando para rodar a aplicação
#CMD ["java", "-jar", "app.jar"]
