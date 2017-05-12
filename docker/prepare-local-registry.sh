#!/usr/bin/env bash
set -e
set -E
#TODO Prodcopy läuft damit nicht

docker pull jboss/wildfly
docker tag jboss/wildfly registry:5000/jboss-wildfly
docker rmi jboss/wildfly
docker push registry:5000/jboss-wildfly
docker pull jboss/wildfly:8.2.1.Final
docker tag jboss/wildfly:8.2.1.Final registry:5000/jboss-wildfly:8.2.1.Final
docker rmi jboss/wildfly:8.2.1.Final
docker push registry:5000/jboss-wildfly:8.2.1.Final
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

docker pull ubuntu:15.10
docker tag ubuntu:15.10 registry:5000/ubuntu:15.10
docker push registry:5000/ubuntu:15.10
docker rmi ubuntu:15.10

./gradlew -Dorg.gradle.project.dockerPush=true --info dockerBase dockerJava dockerMysql dockerOracleXE dockerPetClinic dockerPetClinicOracleXE dockerPetClinicOracleXEFlyway
