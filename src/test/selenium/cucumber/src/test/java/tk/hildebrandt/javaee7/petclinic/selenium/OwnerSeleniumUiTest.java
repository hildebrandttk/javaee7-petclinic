package tk.hildebrandt.javaee7.petclinic.selenium;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import static org.jboss.arquillian.graphene.Graphene.goTo;

import cucumber.api.junit.Cucumber;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Glues;
import tk.hildebrandt.javaee7.petclinic.PetStoreDeployment;

@RunWith(ArquillianCucumber.class)
@Cucumber.Options(format = { "pretty", "html:reports" })
@Features("tk/hildebrandt/javaee7/petclinic/selenium/Owner.feature")
@Glues(OwnerStepDefinitions.class)
public class OwnerSeleniumUiTest {

   @Deployment(testable = false)
   public static WebArchive createFullDeployment() {
      return PetStoreDeployment.createFullDeployment();
   }
}
