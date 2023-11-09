FROM openjdk:8-jdk-alpine
ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
ENTRYPOINT ["java","-jar", "gestion-station-ski-1.0.jar"]
