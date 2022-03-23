FROM openjdk

WORKDIR /app

COPY target/rentgames-0.0.1-SNAPSHOT.jar /app/rentgames.jar

ENTRYPOINT ["java","-jar", "rentgames.jar"] 