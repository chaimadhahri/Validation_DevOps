version: "3.9"

services:
  Validation_DevOps:
    container_name: Validation_DevOps
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8089:8089"
    environment:
      - spring.datasource.url=jdbc:mysql://localhost:3306/stationSki?createDatabaseIfNotExist=true
      - spring.datasource.username=root
      - spring.datasource.password=
    image: station-ski
    command: mvn test
    restart: unless-stopped
