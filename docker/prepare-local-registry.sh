#!/usr/bin/env bash
set -e
set -E

docker pull jboss/wildfly:10.0.0.Final
docker tag jboss/wildfly:10.0.0.Final registry:5000/jboss-wildfly:10.0.0.Final
docker rmi  jboss/wildfly:10.0.0.Final
docker push registry:5000/jboss-wildfly:10.0.0.Final

docker pull selenium/hub:3.4.0
docker tag selenium/hub:3.4.0 registry:5000/selenium-hub:3.4.0
docker push registry:5000/selenium-hub:3.4.0
docker rmi selenium/hub:3.4.0
docker pull selenium/node-chrome-debug:3.4.0
docker tag selenium/node-chrome-debug:3.4.0 registry:5000/selenium-node-chrome-debug:3.4.0
docker push registry:5000/selenium-node-chrome-debug:3.4.0
docker rmi selenium/node-chrome-debug:3.4.0
docker pull selenium/node-firefox-debug:3.4.0
docker tag selenium/node-firefox-debug:3.4.0 registry:5000/selenium-node-firefox-debug:3.4.0
docker push registry:5000/selenium-node-firefox-debug:3.4.0
docker rmi selenium/node-firefox-debug:3.4.0
docker pull selenium/node-chrome:3.4.0
docker tag selenium/node-chrome:3.4.0 registry:5000/selenium-node-chrome:3.4.0
docker push registry:5000/selenium-node-chrome:3.4.0
docker rmi selenium/node-chrome:3.4.0
docker pull selenium/node-firefox:3.4.0
docker tag selenium/node-firefox:3.4.0 registry:5000/selenium-node-firefox:3.4.0
docker push registry:5000/selenium-node-firefox:3.4.0
docker rmi selenium/node-firefox:3.4.0
docker pull selenium/node-phantomjs:3.4.0
docker tag selenium/node-phantomjs:3.4.0 registry:5000/selenium-node-phantomjs:3.4.0
docker push registry:5000/selenium-node-phantomjs:3.4.0
docker rmi selenium/node-phantomjs:3.4.0

docker pull ubuntu
docker tag ubuntu registry:5000/ubuntu
docker push registry:5000/ubuntu
docker rmi ubuntu

docker pull ubuntu:14.04
docker tag ubuntu:14.04 registry:5000/ubuntu:14.04
docker push registry:5000/ubuntu:14.04
docker rmi ubuntu:14.04

docker pull ubuntu:16.04
docker tag ubuntu:16.04 registry:5000/ubuntu:16.04
docker push registry:5000/ubuntu:16.04
docker rmi ubuntu:16.04

./gradlew -Dorg.gradle.project.dockerPush=true --info dockerBase dockerJava dockerMysql dockerOracleXE dockerPetClinic dockerPetClinicOracleXE dockerPetClinicOracleXEFlyway
