package tk.hildebrandt.javaee7.petclinic.selenium;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OwnerStepDefinitions {

   @Given("^browser on find owner page$")
   public void goToFindOwnerPage() {
      final FindOwnersPage findOwnersPage = new FindOwnersPage();
      findOwnersPage.get();
      findOwnersPage.assertIsLoaded();
   }

   @Given("^browser on find owner result page$")
   public void goToFindOwnerResultPage() {
      new HelloPage()
         .get()
         .toFindOwners()
         .searchForOwner("");
   }

   @When("^create new owner (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*)$")
   public void createNewOwner(
      String firstName, String
      lastName, String street, String
         city, String phone) {
      new FindOwnersPage().openNewOwnersPage()
         .addNewOwner(firstName, lastName, street, city, phone);
   }

   @Then("^owner (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*) exists$")
   public void assertOwnerCreated(
      String firstName, String
      lastName, String street, String
         city, String phone) {
      new FindOwnersResultPage().assertOwnerPresent(firstName, lastName, street, city, phone);
   }
}
