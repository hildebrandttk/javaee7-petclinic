package tk.hildebrandt.javaee7.petclinic

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
import org.woehlke.javaee7.petclinic.web.SpecialtyController
import org.woehlke.javaee7.petclinic.web.SpecialtyConverter
import org.woehlke.javaee7.petclinic.web.VetController

class PetStoreDeployments {

   private static final String BASE = "../../../../"
   private static final String WEBAPP_SRC = BASE+ "src/main/webapp";
   private static final String POM_PATH = BASE + "pom.xml"

   public static WebArchive createFullDeployment() {
      File[] deps = Maven.resolver().loadPomFromFile(POM_PATH).importRuntimeDependencies().resolve().
         withTransitivity().asFile();
      WebArchive war = null;
      try {
         war = ShrinkWrap.create(WebArchive.class, "petclinic-all.war")
            .addClasses(SpecialtyController.class, SpecialtyDao.class, SpecialtyDaoImpl.class,SpecialtyConverter,
            VetController.class,  VetDao, VetDaoImpl,
            OwnerController.class, PetTypeController.class, LanguageBean.class,
            OwnerService.class, OwnerServiceImpl.class,
            OwnerDao.class, OwnerDaoImpl.class, PetDao.class, PetDaoImpl.class,
            VisitDao.class, VisitDaoImpl.class,
            PetTypeDao.class, PetTypeDaoImpl.class,
            Owner.class, Pet.class, PetType.class,
            Specialty.class, Vet.class, Visit.class)
            .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
            .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
            Filters.include('.*\\.xhtml$'))
            .addAsResource("META-INF/persistence.xml")
            .addAsResource("messages_de.properties")
            .addAsResource("messages_en.properties")
            .addAsLibraries(deps)
            .addAsWebInfResource(
            new StringAsset("<faces-config version=\"2.2\"/>"),
            "faces-config.xml");
      } catch (Exception e) {
         throw new IllegalStateException(e)
      }
      return war;
   }
}
