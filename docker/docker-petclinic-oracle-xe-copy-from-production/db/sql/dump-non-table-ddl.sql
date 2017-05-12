SET long 1000000
SET HEAD OFF
SET echo off
SET pagesize 0
SET verify off
SET feedback off
SET linesize 32767
SET trimspool on
SET longchunksize 32767
SPOOL /root/db/tmp/non-table-ddl.out

-- http://przemyslawkruglej.com/archive/2014/09/how-to-get-referential-constraints-using-dbms_metadata/
-- Tabellen ohne REFERENCE Constraints
BEGIN
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'SQLTERMINATOR', true);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'CONSTRAINTS_AS_ALTER', true);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'REF_CONSTRAINTS', false);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'CONSTRAINTS', false);
   DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.session_transform,
                                     'SEGMENT_ATTRIBUTES', false);
END;
/

SELECT 'whenever sqlerror exit sql.sqlcode
   set sqlblanklines on
   SET DEFINE OFF
' from dual;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER in ('PETCLINIC'))
   AND OBJECT_TYPE='SEQUENCE'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='SYNONYM'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='VIEW'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='TYPE'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='TYPE_BODY'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='PACKAGE'
 ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='FUNCTION'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='PROCEDURE'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL(REPLACE(object_type,' ','_'), object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='TRIGGER'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL('PACKAGE_SPEC', object_name, owner)
FROM all_OBJECTS
WHERE (OWNER = 'PETCLINIC')
      AND OBJECT_TYPE='PACKAGE'  ORDER BY object_name;

SELECT DBMS_METADATA.GET_DDL('PACKAGE_BODY', object_name, owner)
 FROM all_OBJECTS
 WHERE (OWNER = 'PETCLINIC')
   AND OBJECT_TYPE='PACKAGE BODY'  ORDER BY object_name;

select 'EXEC DBMS_UTILITY.compile_schema(schema => ''PETCLINIC'');' from dual;

SPOOL off