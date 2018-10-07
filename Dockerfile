FROM openjdk:10-slim
EXPOSE 8080/tcp
ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/betting/app.jar
WORKDIR /usr/share/betting
ENTRYPOINT ["java", "-Xmx256M", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar", "--spring.profiles.active=develop,docker"]
