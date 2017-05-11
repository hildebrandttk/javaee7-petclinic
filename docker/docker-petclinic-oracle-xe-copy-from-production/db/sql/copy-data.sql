whenever sqlerror exit sql.sqlcode

BEGIN
   FOR item IN (
   select ao.object_Name from all_OBJECTS ao where ao.owner='PETCLINIC' AND ao.OBJECT_TYPE='TABLE'
   )
   LOOP
      EXECUTE IMMEDIATE 'INSERT INTO "'||item.object_Name||'" SELECT * FROM "'||item.object_Name||'"@source_db';
   END LOOP;
END;
/
