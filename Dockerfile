FROM eclipse-temurin:21-jdk as build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]