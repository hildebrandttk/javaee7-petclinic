CREATE TABLE "PETS" (
   "ID"         NUMBER(19, 0)      NOT NULL ENABLE,
   "BIRTH_DATE" DATE               NOT NULL ENABLE,
   "NAME"       VARCHAR2(255 CHAR) NOT NULL ENABLE,
   "OWNER_ID"   NUMBER(19, 0)      NOT NULL ENABLE,
   "TYPE_ID"    NUMBER(19, 0)      NOT NULL ENABLE,
   PRIMARY KEY ("ID"),
   CONSTRAINT "FK_HIREMAXOOQSCPN6C1KW3TVSIA" FOREIGN KEY ("OWNER_ID") REFERENCES "OWNERS" ("ID") ENABLE,
   CONSTRAINT "FK_5AVF7QBBCWDRFSPMFKTMMV197" FOREIGN KEY ("TYPE_ID") REFERENCES "TYPES" ("ID") ENABLE
);
