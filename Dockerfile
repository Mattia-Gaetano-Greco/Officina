FROM maven:3.9.6-sapmachine-17
WORKDIR /app

EXPOSE 8081

COPY src ./src/
COPY pom.xml .
#RUN mvn spring-boot:**

CMD mvn spring-boot:run