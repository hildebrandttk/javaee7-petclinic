package org.woehlke.javaee7.petclinic.services;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.PersistenceTest;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.woehlke.javaee7.petclinic.dao.OwnerDaoImpl;
import org.woehlke.javaee7.petclinic.entities.Owner;
import org.woehlke.javaee7.petclinic.entities.Pet;
import org.woehlke.javaee7.petclinic.entities.Visit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
@PersistenceTest
@UsingDataSet("OwnerServiceImplTest.yml")
@Cleanup(strategy = CleanupStrategy.USED_TABLES_ONLY)
public class OwnerServiceImplTest {
   private static final String BASE = "../../../../";
   private static final String POM_PATH = BASE + "pom.xml";

   @Deployment
   public static WebArchive createDaoDeployment() {
      File[] deps = Maven.resolver().loadPomFromFile(POM_PATH).importRuntimeDependencies().resolve().
         withTransitivity().asFile();
      WebArchive war;
      try {
         war = ShrinkWrap.create(WebArchive.class, "service-deployment.war")
            .addClasses(OwnerServiceImpl.class, OwnerService.class)
            .addPackage(OwnerDaoImpl.class.getPackage().getName())
            .addPackage(Owner.class.getPackage().getName())
            .addAsResource("META-INF/persistence.xml")
            .addAsLibraries(deps);
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
      return war;
   }

   @PersistenceContext(unitName = "javaee7petclinic")
   private EntityManager entityManager;

   @Inject
   private OwnerService ownerService;

   @Test
   public void testAddVisit() {
      final Pet petReference = entityManager.getReference(Pet.class, 11L);
      final String testVisitDescription = "Test visit " + new Date();
      ownerService.addNewVisit(new Visit(petReference, testVisitDescription, new Date()));
      final Pet pet = entityManager.find(Pet.class, 11L);
      final List<Visit> visits = pet.getVisits();
      assertThat(visits, CoreMatchers.<Visit>hasItem(hasProperty("description", is(equalTo(testVisitDescription)))));
   }
}
