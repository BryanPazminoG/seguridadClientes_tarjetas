FROM openjdk:21

WORKDIR /app

COPY target/tarjetas-0.0.1-SNAPSHOT.jar .

EXPOSE 8084

CMD ["java", "-jar", "tarjetas-0.0.1-SNAPSHOT.jar"]

