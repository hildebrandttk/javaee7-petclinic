package tk.hildebrandt.tk.javaee7.petclinic.geb.junit

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.persistence.UsingDataSet
import org.jboss.shrinkwrap.api.spec.WebArchive
import tk.hildebrandt.javaee7.petclinic.PetStoreDeployments

@ArquillianSuiteDeployment
class Deployments {

   @Deployment
   @UsingDataSet("UiTests.yml") //add to package
   public static WebArchive createFullDeployment() {
      return PetStoreDeployments.createFullDeployment().addClass(Test00CleanUp.class)
   }
}
