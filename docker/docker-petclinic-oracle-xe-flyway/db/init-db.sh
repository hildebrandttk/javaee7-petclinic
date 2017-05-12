#!/usr/bin/env bash
set -e
set -E

ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
export ORACLE_HOME

function execute_script {
   echo apply "$1"
   cat "$1" | $ORACLE_HOME/bin/sqlplus SYS/manager@localhost/xe as sysdba
   echo "$1 applied $?"
}

execute_script /root/db/sql/init/001_CREATE_USER_PETCLINIC.sql

/root/flyway/flyway -configFile=/root/db/flyway.conf migrate