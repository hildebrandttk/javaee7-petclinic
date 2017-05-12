# Java EE 7 Petclinic #

Based on https://github.com/phasenraum2010/javaee7-petclinic from Thomas Wöhlke. 

## install JBoss Wildfly ##

## Functional Tests with Selenium2 Webdriver, Arquillian Drone and Graphene ##

To learn more about Arquillian please refer to the [Arquillian Guides](http://arquillian.org/guides/)

## build and run ##

# docker samples #

## download required files ##

You have to download some files and place it within the docker tree
+ Oracle Database Express Edition 11g Release 2 for Linux x64 to docker/docker-oracle-xe/ and convert it to an deb file
http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html
+ Oracle JDK (Linux x64, tar.gz) to  docker/docker-java/jdk-8u131-linux-x64.tar.gz
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

## setup local docker registry ##
You can use the docker-compose file in docker/registry to start a local registry. The setup required local ssl client certificate authentication.

You have to copy 
+ ca.crt
+ client.cert
+ client.key

to /etc/docker/certs.d/registry:5000 and add an entry with registry and an ip address (not 127.0.0.1) of your computer (or docker host) to your hosts file.

## preparing docker registry ##
run prepare-local-registry.sh from directory docker.

# Using application #

TODO

## First Steps ##

add some PetTypes like dog,cat,mouse,...

add some Specialties for Vetinarians like dentist, anesthetist, radiology,...

add a Veterinarian

add an Owner, add him am a Pet and his Pet a visit.

## visit Spring Petclinic ##

https://github.com/spring-projects/spring-petclinic

