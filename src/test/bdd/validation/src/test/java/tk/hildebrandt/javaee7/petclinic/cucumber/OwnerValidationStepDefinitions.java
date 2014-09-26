package tk.hildebrandt.javaee7.petclinic.cucumber;

import java.lang.reflect.Field;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.woehlke.javaee7.petclinic.entities.Owner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OwnerValidationStepDefinitions {
   @Inject
   private Validator validator;
   private Owner owner;

   private Set<ConstraintViolation<Owner>> constraintViolations;
   @Given("^Valides Benutzerobjekt$")
   public void valid_owner_instance() throws Throwable {
      owner = new Owner();
      owner.setLastName("Wurst");
      owner.setFirstName("Hans");
      owner.setAddress("Some street");
      owner.setCity("some city");
      owner.setTelephone("0800666666");
   }

   @Given("^Ändere (.*) auf null$")
   public void setFieldWithNameToValue(String field) throws IllegalAccessException, NoSuchFieldException {
      final Field declaredField = owner.getClass().getDeclaredField(field);
      declaredField.setAccessible(true);
      declaredField.set(owner, null);
   }
   @Given("^Ändere (.*) auf \"(.*)\"$")
   public void setFieldWithNameToValue(String field, String newValue) throws IllegalAccessException, NoSuchFieldException {
      final Field declaredField = owner.getClass().getDeclaredField(field);
      declaredField.setAccessible(true);
      declaredField.set(owner, newValue);
   }

   @When("^die Validierung ausgeführt wird$")
   public void validation_is_executed() throws Throwable {
      constraintViolations = validator.validate(owner);
   }

   @Then("^Validierungsfehler mit Nachricht: \"(.*)\" ist vorhanden$")
   public void validation_error_with_message_is_present(String message) throws Throwable {
      assertEquals("Falsche Anzahl von Fehlermeldungen", 1,constraintViolations.size());
      for (ConstraintViolation<Owner> constraintViolation : constraintViolations) {
         final String pathWithMessage = constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage();
         assertEquals(message, pathWithMessage);
      }
   }

   @Then("Object is valid")
   public void objectIsValid() throws Throwable {
      assertTrue(constraintViolations.isEmpty());
   }
}
