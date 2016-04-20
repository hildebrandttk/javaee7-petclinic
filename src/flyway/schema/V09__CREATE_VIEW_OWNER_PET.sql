create view owner_with_pets AS
SELECT O.ID owner_id, O.ADDRESS, O.CITY, O.FIRST_NAME, O.LAST_NAME, O.TELEPHONE, p.ID pet_id, p.BIRTH_DATE pet_birthdate, p.NAME pet_name, t.name pet_type FROM owners O JOIN PETS p on p.OWNER_ID=o.id join types t on t.id=p.TYPE_ID;