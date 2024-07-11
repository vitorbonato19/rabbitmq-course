FROM openjdk:21

COPY target/rabbitmq-springweb-0.0.1-SNAPSHOT.jar proposta.jar

ENTRYPOINT ["java", "-jar","proposta.jar"]

