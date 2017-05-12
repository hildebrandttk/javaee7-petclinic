#!/usr/bin/env bash
set -e
set -E
USER=$1
PASSWORD=$2
SCRIPTS=$3

java \
   -cp /root/dbdeploy/dbdeploy-cli-3.0M3.jar:/usr/share/java/mysql-connector-java.jar \
   com.dbdeploy.CommandLineTarget \
   -U $USER \
   -P $PASSWORD \
   -D com.mysql.jdbc.Driver \
   -u jdbc:mysql://127.0.0.1/$USER \
   -d mysql \
   -s $SCRIPTS