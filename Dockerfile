
FROM maven:3.9.5-amazoncorretto-21 AS build


WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline


COPY src ./src
RUN mvn clean package -DskipTests


FROM amazoncorretto:21-al2-jdk


WORKDIR /app


COPY --from=build /app/target/*.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
