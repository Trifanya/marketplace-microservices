FROM openjdk:19-jdk-slim
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT-exec.jar
WORKDIR /application
COPY ${JAR_FILE} api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]