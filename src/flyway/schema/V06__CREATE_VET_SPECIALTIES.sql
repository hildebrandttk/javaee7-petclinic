CREATE TABLE "VET_SPECIALTIES" (
   "VET_ID" NUMBER(19,0) NOT NULL ENABLE,
	"SPECIALTY_ID" NUMBER(19,0) NOT NULL ENABLE,
	 PRIMARY KEY ("VET_ID", "SPECIALTY_ID"),
	 CONSTRAINT "FK_F4QD88FLIT0KFTBMMJLXAGENG" FOREIGN KEY ("SPECIALTY_ID") REFERENCES "SPECIALTIES" ("ID") ENABLE,
	 CONSTRAINT "FK_EC5J1TLC75PKQ99VVFGF8QTMN" FOREIGN KEY ("VET_ID") REFERENCES "VETS" ("ID") ENABLE
);
