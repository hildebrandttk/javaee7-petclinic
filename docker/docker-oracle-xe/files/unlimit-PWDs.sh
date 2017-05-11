#!/usr/bin/env bash
set -e
set -E

. /etc/profile

echo "whenever sqlerror exit sql.sqlcode
alter profile DEFAULT  limit password_life_time UNLIMITED;
ALTER USER system IDENTIFIED BY manager;
ALTER USER sys IDENTIFIED BY manager;" | $ORACLE_HOME/bin/sqlplus system/oracle@localhost/XE