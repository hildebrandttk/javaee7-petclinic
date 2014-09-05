Feature: Benutzervalidierung

   Scenario: Vorname muss gesetzt sein
      Given Valides Benutzerobjekt
      Given Ändere firstName auf null
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "firstName darf nicht leer sein" ist vorhanden

   Scenario: Vornamen mit weniger als 2 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere firstName auf "1"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "firstName muss zwischen 2 und 30 Zeichen lang sein" ist vorhanden

   Scenario: Vornamen mit 2 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere firstName auf "12"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Vornamen mit 30 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere firstName auf "123456789012345678901234567890"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Vornamen mit mehr als 30 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere firstName auf "1234567890123456789012345678901"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "firstName muss zwischen 2 und 30 Zeichen lang sein" ist vorhanden

   Scenario: Nachname muss gesetzt sein
      Given Valides Benutzerobjekt
      Given Ändere lastName auf null
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "lastName darf nicht leer sein" ist vorhanden

   Scenario: Nachnamen mit weniger als 2 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere lastName auf "1"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "lastName muss zwischen 2 und 30 Zeichen lang sein" ist vorhanden

   Scenario: Nachnamen mit 2 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere lastName auf "12"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Nachnamen mit 30 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere lastName auf "123456789012345678901234567890"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Nachnamen mit mehr als 30 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere lastName auf "1234567890123456789012345678901"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "lastName muss zwischen 2 und 30 Zeichen lang sein" ist vorhanden

   Scenario: Ort muss gesetzt sein
      Given Valides Benutzerobjekt
      Given Ändere city auf null
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "city darf nicht leer sein" ist vorhanden

   Scenario: Ortsnamen mit weniger als 2 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere city auf "1"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "city muss zwischen 2 und 30 Zeichen lang sein" ist vorhanden

   Scenario: Ortsnamen mit 2 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere city auf "12"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Ortsnamen mit 30 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere city auf "123456789012345678901234567890"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Ortsnamen mit mehr als 30 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere city auf "1234567890123456789012345678901"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "city muss zwischen 2 und 30 Zeichen lang sein" ist vorhanden

   Scenario: Adresse muss gesetzt sein
      Given Valides Benutzerobjekt
      Given Ändere address auf null
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "address darf nicht leer sein" ist vorhanden

   Scenario: Adressen mit weniger als 2 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere address auf "1"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "address muss zwischen 2 und 50 Zeichen lang sein" ist vorhanden

   Scenario: Adressen mit 2 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere address auf "12"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Adressen mit 50 Zeichen sind valide
      Given Valides Benutzerobjekt
      Given Ändere address auf "12345678901234567890123456789012345678901234567890"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Adressen mit mehr als 50 Zeichen sind nicht valide
      Given Valides Benutzerobjekt
      Given Ändere address auf "123456789012345678901234567890123456789012345678901"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "address muss zwischen 2 und 50 Zeichen lang sein" ist vorhanden

   Scenario: Telefonnummer muss gesetzt sein
      Given Valides Benutzerobjekt
      Given Ändere telephone auf null
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "telephone darf nicht leer sein" ist vorhanden

   Scenario: Telefonnummer mit weniger als 3 Ziffern ist nicht valide
      Given Valides Benutzerobjekt
      Given Ändere telephone auf "12"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "telephone muss zwischen 3 und 10 Ziffern lang sein" ist vorhanden

   Scenario: Telefonnummer mit 3 Ziffern ist valide
      Given Valides Benutzerobjekt
      Given Ändere telephone auf "112"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Telefonnummer mit 10 Ziffern ist valide
      Given Valides Benutzerobjekt
      Given Ändere telephone auf "1234567890"
      When die Validierung ausgeführt wird
      Then Object is valid

   Scenario: Telefonnummer mit 11 Ziffern ist nicht valide
      Given Valides Benutzerobjekt
      Given Ändere telephone auf "12345678901"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "telephone muss zwischen 3 und 10 Ziffern lang sein" ist vorhanden

   Scenario: Telefonnummer mit Buchstaben ist nicht valide
      Given Valides Benutzerobjekt
      Given Ändere telephone auf "123454a890"
      When die Validierung ausgeführt wird
      Then Validierungsfehler mit Nachricht: "telephone numerischer Wert außerhalb erlaubten Wertebereichs (<11 Ziffern>,<0 Ziffern> erwartet)" ist vorhanden
