FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/*.jar spring-webflux-ci-cd.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "spring-webflux-ci-cd.jar"]
