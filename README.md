# Mediscreen Assess Microservice

Mediscreen Assess Microservice is a part of mediscreen application.

Made with Spring Boot application.

## Technologies
- Java 11
- Maven
- Spring
- JUnit
- Jacoco

## About this microservice
### There is 3 containers with these specific names :
- microserviceassess

docker compose alias : assess,
This container work on port 80

- microservicepatienthistory

docker compose alias : patienthistory,
This container work on port 81

- microservicepatient

docker compose alias : patient,
This container work on port 82

###How to use
Using the terminal, go to the pom.xml folder and use this command :

    mvn package -DskipTests

It will create a package named microserviceAssess-2.6.4 in the target folder.
You have to rename it microserviceAssess.

Then, go to the dockerfile folder and use this command :

    docker build -t microserviceassess .
 
Look for microservicepatienthistory and microservicepatient Readme for further information 
concerning creating their own images.

After you create each image for each microservice, use the docker-compose.yml in the MediscreenAssessMicroservice project.
Use this command : 

    docker compose up

You can now use this microservice to generate report for each patient.

CAREFUL : Report will appear inside the logger

## Authors

Our code squad : Francky

## Licensing

This project was built under the Creative Commons Licence.
