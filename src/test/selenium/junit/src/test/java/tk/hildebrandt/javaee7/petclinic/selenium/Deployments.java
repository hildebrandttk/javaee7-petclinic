package tk.hildebrandt.javaee7.petclinic.selenium;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import tk.hildebrandt.javaee7.petclinic.PetStoreDeployment;

@ArquillianSuiteDeployment
public class Deployments {

   @Deployment(testable = false)
   public static WebArchive createFullDeployment() {
      return PetStoreDeployment.createFullDeployment();
   }
}
