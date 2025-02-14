FROM openjdk:21-jdk
MAINTAINER Thien Nguyen <thnguyen24@gmail.com>

ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/tnn/tnn-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/share/tnn/tnn-service.jar"]