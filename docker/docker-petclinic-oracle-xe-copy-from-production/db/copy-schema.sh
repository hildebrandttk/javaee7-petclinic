#!/usr/bin/env bash
set -e
set -E

SOURCE_HOST=$1
SOURCE_PORT=$2
SOURCE_SID=$3
SOURCE_USER=$4
SOURCE_PWD=$5

ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
NLS_LANG=GERMAN_GERMANY.UTF8
export ORACLE_HOME NLS_LANG

$ORACLE_HOME/bin/sqlplus ${SOURCE_USER}/${SOURCE_PWD}@${SOURCE_HOST}:${SOURCE_PORT}/${SOURCE_SID} @/root/db/sql/dump-tables-without-constraints.sql #1>/dev/null
echo "dump-tables-without-constraints $?"
cat /root/db/tmp/tables_without_constraints.out | sed "s/DATE DEFAULT '31.12.9999'/DATE DEFAULT to_date('31.12.9999','DD.MM.YYYY')/g" | $ORACLE_HOME/bin/sqlplus PETCLINIC/petclinic@localhost/XE
echo "tables_without_constraints $?"

echo "create public database link source_db connect to ${SOURCE_USER} identified by \"${SOURCE_PWD}\"  USING '${SOURCE_HOST}:${SOURCE_PORT}/${SOURCE_SID}';"  |$ORACLE_HOME/bin/sqlplus SYSTEM/manager@localhost/XE

echo "copy Data"
$ORACLE_HOME/bin/sqlplus PETCLINIC/petclinic@localhost/XE @/root/db/sql/copy-data.sql
echo "Finished copy Data $?"

echo "Generate reference_constraint_ddl"
$ORACLE_HOME/bin/sqlplus ${SOURCE_USER}/${SOURCE_PWD}@${SOURCE_HOST}:${SOURCE_PORT}/${SOURCE_SID} @/root/db/sql/dump-constraint-ddl.sql #1>/dev/null
echo "Apply reference_constraint_ddl"
cat /root/db/tmp/constraint_ddl.out |$ORACLE_HOME/bin/sqlplus PETCLINIC/petclinic@localhost/XE
echo "reference_constraint_ddl $?"

echo "Generate non-table-ddl"
$ORACLE_HOME/bin/sqlplus ${SOURCE_USER}/${SOURCE_PWD}@${SOURCE_HOST}:${SOURCE_PORT}/${SOURCE_SID} @/root/db/sql/dump-non-table-ddl.sql #1>/dev/null
echo "dump-non-table-ddl.sql $?"
echo "Apply dump-non-table-ddl.out"
cat /root/db/tmp/non-table-ddl.out | $ORACLE_HOME/bin/sqlplus PETCLINIC/petclinic@localhost/XE
echo "non-table-ddl $?"

echo "drop public database link source_db;"  |$ORACLE_HOME/bin/sqlplus SYSTEM/manager@localhost/XE

echo "EXEC DBMS_UTILITY.compile_schema(schema => 'PETCLINIC');"  |$ORACLE_HOME/bin/sqlplus SYSTEM/manager@localhost/XE

