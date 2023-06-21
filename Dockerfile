FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/Dumpy-url-2.jar Dumpy-url-2.jar
ENTRYPOINT ["java","-jar","/Dumpy-url-2.jar"]