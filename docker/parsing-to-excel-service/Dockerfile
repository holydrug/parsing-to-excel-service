FROM maven:3.6.0-jdk-8-slim AS build_parsing_excel
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package

FROM openjdk:8
ARG JAR_FILE=/home/app/target/*.jar
COPY --from=build_parsing_excel ${JAR_FILE} parsing-excel-service.jar
ENTRYPOINT ["java","-jar","/parsing-excel-service.jar"]