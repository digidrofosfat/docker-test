FROM adoptopenjdk/openjdk11


COPY target/demo*.jar demo.jar
COPY entrypoint.sh entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
