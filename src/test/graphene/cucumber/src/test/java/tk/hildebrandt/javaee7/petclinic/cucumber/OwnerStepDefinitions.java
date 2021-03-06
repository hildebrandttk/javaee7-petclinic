package tk.hildebrandt.javaee7.petclinic.cucumber;

import org.jboss.arquillian.graphene.page.Page;
import org.woehlke.javaee7.petclinic.web.pages.FindOwnersPage;
import org.woehlke.javaee7.petclinic.web.pages.HelloPage;

import static org.jboss.arquillian.graphene.Graphene.goTo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OwnerStepDefinitions {



   @Given("^browser on find owner result page$")
   public void goToFindOwnerResultPage() {
//      to(HelloPage)
//         .toFindOwners()
//         .searchForOwner("")
   }

   @When("^create new owner (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*)$")
   public void createNewOwner(
      String firstName, String
      lastName, String street, String
         city, String phone) {
//      ((FindOwnersPage) page).openNewOwnersPage()
//         .addNewOwner(firstName, lastName, street, city, phone)
   }

   @Then("^owner (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*) exists$")
   public void assertOwnerCreated(
      String firstName, String
      lastName, String street, String
         city, String phone) {
//      ((FindOwnersResultPage) page).assertOwnerPresent(firstName, lastName, street, city, phone)
   }
}
