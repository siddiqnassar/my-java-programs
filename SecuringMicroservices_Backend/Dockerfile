FROM java:8

VOLUME /var/lib/docker-images

EXPOSE 9210

ADD /target/SecuringMicroservices_Backend.jar SecuringMicroservices_Backend.jar

ENTRYPOINT ["java", "-jar", "SecuringMicroservices_Backend.jar"]
