FROM eclipse-temurin:17-jdk-alpine
COPY ./target/eboutique-0.0.1-SNAPSHOT.jar /opt/app/
WORKDIR /opt/app/
ENTRYPOINT ["java","-jar","eboutique-0.0.1-SNAPSHOT.jar"]

