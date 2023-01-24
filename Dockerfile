FROM openjdk:11
WORKDIR /usr/src/myapp
COPY build/libs/*.jar myapp.jar
COPY lib lib
EXPOSE 8080
CMD ["java", "-jar", "myapp.jar"]
