SET long 100000
SET HEAD OFF
SET echo off
SET pagesize 0
SET verify off
SET feedback off
SET linesize 32767
SET trimspool on
SET longchunksize 32767
SPOOL /root/db/tmp/constraint_ddl.out

whenever sqlerror exit sql.sqlcode

SELECT 'whenever sqlerror exit sql.sqlcode
set sqlblanklines on
SET DEFINE OFF
set sqlterminator '';''
'
FROM dual;

BEGIN
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'SQLTERMINATOR', true);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'SEGMENT_ATTRIBUTES', false);
   DBMS_METADATA.SET_TRANSFORM_PARAM(dbms_metadata.session_transform,
                                     'PARTITIONING', FALSE);
 END;
/

-- PRIMARY Constraints
select dbms_metadata.get_ddl('CONSTRAINT', constraint_name)
from (select DISTINCT c.constraint_name constraint_name, c.table_name
      from user_constraints c
      where c.TABLE_NAME not like '%_BCK%' AND
         c.constraint_type = 'P'  and not exists(select 1 from USER_INDEXES i where i.INDEX_TYPE!='NORMAL' AND i.INDEX_NAME=c.CONSTRAINT_NAME) ORDER BY c.table_name);



-- Unique und Check constraints
select dbms_metadata.get_ddl('CONSTRAINT', constraint_name)
from (select DISTINCT c.constraint_name
      from user_constraints c
      where c.TABLE_NAME not like '%_BCK%' AND
         c.CONSTRAINT_NAME not like 'BIN%' AND c.constraint_type IN('C','U') ORDER BY c.constraint_name);

-- REFERENCE Constraints
select dbms_metadata.get_ddl('REF_CONSTRAINT', c.constraint_name)
FROM user_constraints c
   join user_constraints rc ON c.R_CONSTRAINT_NAME=rc.CONSTRAINT_NAME
WHERE c.TABLE_NAME not like '%_BCK%'
      AND c.constraint_type = 'R';
SPOOL off