#!/usr/bin/env bash
set -e
set -E
USER=$1
PASSWORD=$2
SCRIPTS=$3
NLS_LANG=GERMAN_GERMANY.UTF8
ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
export ORACLE_HOME NLS_LANG

java \
   -cp /root/dbdeploy/dbdeploy-cli-3.0M3.jar:/u01/app/oracle/product/11.2.0/xe/jdbc/lib/ojdbc6.jar \
   com.dbdeploy.CommandLineTarget \
   -U $USER \
   -P $PASSWORD \
   -D oracle.jdbc.OracleDriver \
   -u jdbc:oracle:thin:@localhost:1521:XE \
   -d ora \
   -o /tmp/consolidated_script_$USER.sql \
   -s $SCRIPTS
/bin/echo "whenever sqlerror exit sql.sqlcode" > /tmp/consolidated_script_${USER}WithErrorHandling.sql && \
   cat /tmp/consolidated_script_${USER}.sql >> /tmp/consolidated_script_${USER}WithErrorHandling.sql
/bin/cat /tmp/consolidated_script_${USER}WithErrorHandling.sql \
   | /u01/app/oracle/product/11.2.0/xe/bin/sqlplus ${USER}/${PASSWORD}@localhost:1521/XE
