FROM openjdk:8
ADD target/EmployeeData-0.0.1-SNAPSHOT.jar EmployeeData-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "EmployeeData-0.0.1-SNAPSHOT.jar"]