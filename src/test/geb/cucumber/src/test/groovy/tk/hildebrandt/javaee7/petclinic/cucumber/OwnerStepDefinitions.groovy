package tk.hildebrandt.javaee7.petclinic.cucumber

import cucumber.api.groovy.Hooks
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import tk.hildebrandt.GebStepDefinitions
import tk.hildebrandt.javaee7.petclinic.geb.pages.FindOwnersPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.FindOwnersResultPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

@Mixin(Hooks)
public class OwnerStepDefinitions extends GebStepDefinitions {

   @Given('^browser on find owner page$')
   public void goToFindOwnerPage() {
      to(HelloPage)
         .toFindOwners()
   }

   @Given('^browser on find owner result page$')
   public void goToFindOwnerResultPage() {
      to(HelloPage)
         .toFindOwners()
         .searchForOwner('')
   }

   @When('^create new owner (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*)$')
   public void createNewOwner(
      String firstName, String
         lastName, String street, String
         city, String phone) {
      ((FindOwnersPage) page).openNewOwnersPage()
         .addNewOwner(firstName, lastName, street, city, phone)
   }

   @Then('^owner (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*) exists$')
   public void assertOwnerCreated(
      String firstName, String
         lastName, String street, String
         city, String phone) {
      ((FindOwnersResultPage) page).assertOwnerPresent(firstName, lastName, street, city, phone)
   }
}
