package tk.hildebrandt.tk.javaee7.petclinic.geb.junit

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment
import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.shrinkwrap.api.Filters
import org.jboss.shrinkwrap.api.GenericArchive
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.StringAsset
import org.jboss.shrinkwrap.api.importer.ExplodedImporter
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.resolver.api.maven.Maven
import org.woehlke.javaee7.petclinic.dao.*
import org.woehlke.javaee7.petclinic.entities.*
import org.woehlke.javaee7.petclinic.services.OwnerService
import org.woehlke.javaee7.petclinic.services.OwnerServiceImpl
import org.woehlke.javaee7.petclinic.web.LanguageBean
import org.woehlke.javaee7.petclinic.web.OwnerController
import org.woehlke.javaee7.petclinic.web.PetTypeController
import tk.hildebrandt.javaee7.petclinic.PetStoreDeployments

@ArquillianSuiteDeployment
class Deployments {

   @Deployment(testable = false)
   public static WebArchive createFullDeployment() {
      return PetStoreDeployments.createFullDeployment()
   }
}
