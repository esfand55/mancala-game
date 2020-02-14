FROM openjdk:8-jre-alpine
COPY target/mancala-web-0.1.0.jar .
CMD /usr/bin/java -Dlogging.path=/log/ -Xmx400m -Xms400m -jar mancala-web-0.1.0.jar
EXPOSE 8080
