FROM openjdk:19-jdk-alpine
COPY target/transaction-statistics-0.0.1-SNAPSHOT.jar transaction-statistics-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/transaction-stats-0.0.1-SNAPSHOT.jar"]