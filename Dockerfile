#
# Build stage
#
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /logistics-server

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

#
# Package stage
#
FROM amazoncorretto:21-alpine-jdk

WORKDIR /logistics-server

COPY --from=build /logistics-server/target/*.jar ./logistics-server.jar

EXPOSE 7079

ENTRYPOINT ["java","-jar","logistics-server.jar"]