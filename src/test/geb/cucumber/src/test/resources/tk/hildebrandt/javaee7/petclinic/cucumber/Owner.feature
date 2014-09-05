Feature: Owner
   Scenario: Create new owner on find owner page
      Given browser on find owner page
      When create new owner Hans Wurst, living Meicastr. 6, Edewecht with phone 44059990
      Then owner Hans Wurst, living Meicastr. 6, Edewecht with phone 44059990 exists
   Scenario: Create new owner on find owner result page
      Given browser on find owner result page
      When create new owner Conchita Wurst, living Frankfurter Str. 6, Wien with phone 44059991
      Then owner Conchita Wurst, living Frankfurter Str. 6, Wien with phone 44059991 exists
