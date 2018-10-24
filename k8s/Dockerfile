FROM openjdk:8-jre
MAINTAINER <sergei@parknav.com>

EXPOSE 8080

RUN mkdir -p /opt/dev/gis-java
COPY gis-java.jar /opt/dev/gis-java/gis-java.jar

WORKDIR /opt/dev/gis-java

ENTRYPOINT exec java -Xms1g -Xmx1g -jar /opt/dev/gis-java/gis-java.jar 