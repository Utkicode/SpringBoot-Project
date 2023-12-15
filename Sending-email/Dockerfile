FROM maven:3.8.1-openjdk-11-slim AS BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/
RUN mvn clean package
RUN ls target
RUN cp target/Sending-email-${VERSION}.jar /build/application.jar
FROM openjdk:11.0.17-jre-slim
WORKDIR /app/
COPY --from=BUILDER /build/application.jar /app/
CMD java -jar /app/application.jar
