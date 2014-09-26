package tk.hildebrandt.javaee7.petclinic.cucumber;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.woehlke.javaee7.petclinic.web.PetStoreDeployment;
import org.woehlke.javaee7.petclinic.web.pages.FindOwnersPage;
import static org.jboss.arquillian.graphene.Graphene.goTo;

import cucumber.api.java.en.Given;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Glues;

@RunWith(ArquillianCucumber.class)
@Cucumber.Options(format = { "pretty", "html:reports" })
@Features("tk/hildebrandt/javaee7/petclinic/cucumber/Owner.feature")
@Glues(OwnerStepDefinitions.class)
@Ignore("Page injection didn't work in combination with ArquillianCucumber.")
public class OwnerGrapheneUiTest {

   @Deployment(testable = false)
   public static WebArchive createFullDeployment() {
      return PetStoreDeployment.createFullDeployment();
   }

   @Page
   private FindOwnersPage findOwnersPage;

   @Given("^browser on find owner page$")
   public void goToFindOwnerPage() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertPageIsLoaded();
   }
}
