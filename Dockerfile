FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/*.war spring-webflux-ci-cd.war
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "spring-webflux-ci-cd.jar"]
