package tk.hildebrandt.javaee7.petclinic.warp;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.woehlke.javaee7.petclinic.web.PetStoreDeployment;
import org.woehlke.javaee7.petclinic.web.pages.HelloPage;
import static org.jboss.arquillian.graphene.Graphene.goTo;

@WarpTest
@RunAsClient
@RunWith(Arquillian.class)
public class Test01Locale {


   @Drone
    private WebDriver driver;

    @ArquillianResource
    private URL deploymentUrl;

   @Deployment(testable = true)
   public static WebArchive createFullDeployment() {
      return PetStoreDeployment
         .createFullDeployment()
         .addClass(LocaleInspection.class);
   }

    @Test
    @InSequence(1)
    public void testOpeningSpecialtiesPage() {
       Warp
          .initiate(new Activity() {
             @Override
             public void perform() {
                goTo(HelloPage.class);
             }
          })
          .inspect(new LocaleInspection())
;
    }
}
