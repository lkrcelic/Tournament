FROM maven:3.8.4-openjdk-17-slim

WORKDIR /tmp
ADD . /tmp

RUN mvn clean install

CMD ["mvn", "spring-boot:run"]
EXPOSE 3000
