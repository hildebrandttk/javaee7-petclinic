Insert into TYPES (ID,NAME) values ('37','cat');
Insert into TYPES (ID,NAME) values ('38','dog');
Insert into TYPES (ID,NAME) values ('39','mouse');

Insert into OWNERS (ID,ADDRESS,CITY,FIRST_NAME,LAST_NAME,TELEPHONE) values ('42','Schoenhauser Allee 42','Berlin','Thomas','Edit','030123456');
Insert into OWNERS (ID,ADDRESS,CITY,FIRST_NAME,LAST_NAME,TELEPHONE) values ('43','Schoenhauser Allee 42','Berlin','Thomas','AddPets','030123456');

Insert into OWNERS (ID,ADDRESS,CITY,FIRST_NAME,LAST_NAME,TELEPHONE) values ('44','Schoenhauser Allee 42','Berlin','Thomas','TestEditPet','030123456');
Insert into PETS (ID,BIRTH_DATE,NAME,OWNER_ID,TYPE_ID) values ('44',to_date('04.08.12','DD.MM.RR'),'Tomcat','44','37');

Insert into OWNERS (ID,ADDRESS,CITY,FIRST_NAME,LAST_NAME,TELEPHONE) values ('45','Schoenhauser Allee 42','Berlin','Thomas','AddVisitToFirstPet','030123456');
Insert into PETS (ID,BIRTH_DATE,NAME,OWNER_ID,TYPE_ID) values ('45',to_date('04.08.12','DD.MM.RR'),'Tomcat','45','37');