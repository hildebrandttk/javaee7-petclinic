package tk.hildebrandt.javaee7.petclinic.cucumber
import cucumber.api.junit.Cucumber
import cucumber.runtime.arquillian.CukeSpace
import cucumber.runtime.arquillian.api.Features
import cucumber.runtime.arquillian.api.Glues
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.runner.RunWith
import tk.hildebrandt.javaee7.petclinic.PetStoreDeployments

@RunWith(CukeSpace.class)
@Cucumber.Options(format = [ "pretty", "html:reports" ])
@Features("tk/hildebrandt/javaee7/petclinic/cucumber/Owner.feature")
@Glues(OwnerStepDefinitions)
public class OwnerGebUiTest {

   @Deployment(testable = false)
   public static WebArchive createFullDeployment() {
      return PetStoreDeployments.createFullDeployment()
   }
}
