SET long 100000
SET HEAD OFF
SET echo off
SET pagesize 0
SET verify off
SET feedback off
SET linesize 32767
SET trimspool on
SET longchunksize 32767
SPOOL /root/db/tmp/tables_without_constraints.out

WHENEVER sqlerror exit sql.sqlcode

SELECT 'whenever sqlerror exit sql.sqlcode
set sqlblanklines on
SET DEFINE OFF
set sqlterminator '';''
'
FROM dual;
BEGIN
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'SQLTERMINATOR', TRUE);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'CONSTRAINTS_AS_ALTER', TRUE);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'REF_CONSTRAINTS', FALSE);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'CONSTRAINTS', FALSE);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'SEGMENT_ATTRIBUTES', FALSE);
   DBMS_METADATA.SET_TRANSFORM_PARAM(dbms_metadata.session_transform,
                                     'PARTITIONING', FALSE);
END;
/

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type, ' ', '_'), object_name, owner)
FROM all_OBJECTS
WHERE OWNER IN ('PETCLINIC') AND OBJECT_TYPE = 'TABLE' AND OBJECT_NAME not like '%_BCK%'
ORDER BY object_name;

SPOOL off