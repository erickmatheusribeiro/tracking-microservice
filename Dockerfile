#
# Build stage
#
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /order-server

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

#
# Package stage
#
FROM amazoncorretto:21-alpine-jdk

WORKDIR /tracking-server

COPY --from=build /tracking-server/target/*.jar ./tracking-server.jar

EXPOSE 7079

ENTRYPOINT ["java","-jar","order-server.jar"]