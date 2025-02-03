FROM openjdk:21-jdk
MAINTAINER Thien Nguyen <thnguyen24@gmail.com>

ARG FAT_JAR
ADD ${FAT_JAR} /usr/share/tnn/tnn-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/share/tnn/tnn-service.jar"]