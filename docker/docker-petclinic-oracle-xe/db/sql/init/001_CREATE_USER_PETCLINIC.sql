whenever sqlerror exit sql.sqlcode

CREATE USER PETCLINIC IDENTIFIED BY petclinic;
grant connect to PETCLINIC;
grant resource to PETCLINIC;
grant create any TABLE to PETCLINIC;
grant ALTER any TABLE to PETCLINIC;
grant create any INDEX to PETCLINIC;
grant create any VIEW to PETCLINIC;
grant create any TRIGGER to PETCLINIC;
grant create any PROCEDURE to PETCLINIC;
grant create any SEQUENCE to PETCLINIC;
grant CREATE ANY SYNONYM to PETCLINIC;
grant CREATE PUBLIC SYNONYM to PETCLINIC;
grant CREATE ANY JOB TO PETCLINIC;
grant select on dba_jobs to PETCLINIC;
grant select on dba_tables to PETCLINIC;
grant select on dba_tab_columns to PETCLINIC;
grant select on dba_jobs to PETCLINIC;
grant select on dba_triggers to PETCLINIC;
grant select on dba_constraints to PETCLINIC;
GRANT SELECT ON V_$SESSION TO PETCLINIC;
grant execute on sys.dbms_lock to PETCLINIC;