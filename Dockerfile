FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests
###

FROM openjdk

WORKDIR /app

COPY --from=build /home/app/target/rentgames-0.0.1-SNAPSHOT.jar /app/rentgame.jar

ENTRYPOINT ["java","-jar", "rentgame.jar"]
