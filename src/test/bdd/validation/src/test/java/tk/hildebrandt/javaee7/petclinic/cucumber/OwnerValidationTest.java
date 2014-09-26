package tk.hildebrandt.javaee7.petclinic.cucumber;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.woehlke.javaee7.petclinic.entities.Owner;
import org.woehlke.javaee7.petclinic.entities.Pet;

import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Glues;

@RunWith(CukeSpace.class)
//@Cucumber.Options(format = { "pretty", "html:reports" })
@Features("tk/hildebrandt/javaee7/petclinic/cucumber/OwnerValidation.feature")
@Glues(OwnerValidationStepDefinitions.class)
public class OwnerValidationTest {

   @Deployment(testable = true)
   public static WebArchive createFullDeployment() {
      return ShrinkWrap.create(WebArchive.class)
         .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
         .addAsResource("tk/hildebrandt/javaee7/petclinic/cucumber/OwnerValidation.feature")
         .addClass(OwnerValidationStepDefinitions.class)
         .addClass(Owner.class).addClass(Pet.class);
   }
}
