FROM openjdk:8

RUN mkdir /api
WORKDIR /api
COPY ./gradlew /api
COPY ./build.gradle /api
COPY ./settings.gradle /api
COPY ./src /api/src
COPY ./gradle /api/gradle
ENTRYPOINT ["sh", "./gradlew", "bootRun"]