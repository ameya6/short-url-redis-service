FROM eclipse-temurin:21
ARG JAR_FILE=target/*.jar
COPY ./target/short-url-redis-service-*.jar app.jar
ENTRYPOINT ["java",  "-jar", "/app.jar"]