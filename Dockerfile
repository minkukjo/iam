FROM adoptopenjdk/openjdk11:latest

ARG HEAP_SIZE
ENV HEAP_SIZE=${HEAP_SIZE:-1024M}
ARG NEW_SIZE
ENV NEW_SIZE=${NEW_SIZE:-512M}

ARG APP_VERSION

ENV ARTIFACT_NAME=whynot.jar

COPY ./build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT java -server \
-Xms${HEAP_SIZE} -Xmx${HEAP_SIZE} \
-XX:NewSize=${NEW_SIZE} -XX:MaxNewSize=${NEW_SIZE} \
-Djava.net.preferIPv4Stack=true \
-Dspring.profiles.appVersion=$APP_VERSION \
-jar $ARTIFACT_NAME