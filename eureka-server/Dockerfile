# образ, на основе которого создается данный образ
FROM openjdk:19-jdk-slim
# переменной JAR_FILE присваивается путь к jar-архиву
ARG JAR_FILE=target/eureka-server-0.0.1-SNAPSHOT-exec.jar
# назначаем рабочую директорию, в которой будут выполняться дальнейшие команды
WORKDIR /application
# jar-файл копируется в папку /application и копии задается имя catalog-service.jar
COPY ${JAR_FILE} eureka-server.jar

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]