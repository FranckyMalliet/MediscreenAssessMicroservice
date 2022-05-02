FROM openjdk:11
EXPOSE 80
VOLUME /tmp
ARG JAR_FILE=target/MediscreenAssessMicroservice.jar
COPY ${JAR_FILE} microserviceAssess.jar
ENTRYPOINT ["java", "-jar", "/microserviceAssess.jar"]