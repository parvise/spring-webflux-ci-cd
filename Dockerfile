FROM eclipse-temurin:22-jdk
WORKDIR /app
COPY target/*.jar springboot-crud-webflux-ci-cd.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "springboot-crud-webflux-ci-cd.jar"]
