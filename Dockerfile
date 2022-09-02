FROM openjdk:8
EXPOSE 8080
ADD target/contest.jar contest.jar
ENTRYPOINT ["java","-jar","/contest.jar"]