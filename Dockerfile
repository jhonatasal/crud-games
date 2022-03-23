FROM openjdk

WORKDIR /app

COPY target/rentgame-0.0.1-SNAPSHOT.jar /app/rentgame.jar

ENTRYPOINT ["java","-jar", "portao.jar"] 