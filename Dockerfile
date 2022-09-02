FROM openjdk:8-jdk-alpine
COPY target/transaction-stats-0.0.1-SNAPSHOT.jar transaction-stats-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/transaction-stats-0.0.1-SNAPSHOT.jar"]