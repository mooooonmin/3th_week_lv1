FROM amazoncorretto:17

WORKDIR /app

COPY build/libs/*.jar board-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "board-0.0.1-SNAPSHOT.jar"]