#!/bin/bash
docker stop db;
docker rm db;
docker stop oandabot;
docker rm oandabot;
./gradlew clean build &&\
cp build/libs/oanda-2.0.jar src/main/resources/docker/app &&\
chmod 765 src/main/resources/docker/app/oanda-2.0.jar &&\
docker build src/main/resources/docker/db -t db &&\
docker run --name db -h db -d db &&\
docker build src/main/resources/docker/app -t oandabot &&\
docker run --name oandabot -p 80:80 -p 8080:8080 --link db:db -d oandabot