FROM maven:3.8-openjdk-17-slim AS build

WORKDIR /usr/app
COPY . .
RUN mvn clean install package -DskipTests


FROM openjdk:22-ea-17-jdk-slim
WORKDIR /usr/app
COPY --from=build ["/usr/app/target/*.jar" ,"./"]
EXPOSE 9093
ENTRYPOINT ["java", "-jar"]
CMD ["*.jar"]