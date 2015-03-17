package org.woehlke.javaee7.petclinic.dao;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.woehlke.javaee7.petclinic.entities.Owner;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
@PersistenceTest
@UsingDataSet("OwnerDaoImplTest.yml")
@Cleanup(strategy = CleanupStrategy.USED_TABLES_ONLY)
public class OwnerDaoImplTest {
   private static final String BASE = "../../../../";
   private static final String POM_PATH = BASE + "pom.xml";
   private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

   @Deployment
   public static WebArchive createDaoDeployment() {
      File[] deps = Maven.resolver().loadPomFromFile(POM_PATH).importRuntimeDependencies().resolve().
         withTransitivity().asFile();
      WebArchive war;
      try {
         war = ShrinkWrap.create(WebArchive.class, "dao-deployment.war")
            .addPackage(OwnerDaoImpl.class.getPackage().getName())
            .addPackage(Owner.class.getPackage().getName())
            .addAsResource("META-INF/persistence.xml")
            .addAsLibraries(deps);
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
      return war;
   }

   //Need this instance for arquillian-dbunit loading
   @SuppressWarnings("UnusedDeclaration")
   @PersistenceContext(unitName = "javaee7petclinic")
   private EntityManager entityManager;

   @Inject
   private OwnerDao ownerDao;

   @Test
   public void testFindOwnersWithVisitWithinGivenTimeFrame_DayForLastVisit() throws ParseException {
      final List<Owner> owners = ownerDao
         .findOwnersWithVisitWithinGivenTimeFrame(
            SIMPLE_DATE_FORMAT.parse("2015-03-11"),
            new Date());
      assertThat(owners, CoreMatchers.<Owner>hasItem(hasProperty("firstName", is(equalTo("Stefan")))));
      assertThat(owners, not(CoreMatchers.<Owner>hasItem(hasProperty("firstName", is(equalTo("Horst"))))));
   }

   @Test
   public void testFindOwnersWithVisitWithinGivenTimeFrame_OnlyOneVisitOnOneDayInPast() throws ParseException {
      final List<Owner> owners = ownerDao
         .findOwnersWithVisitWithinGivenTimeFrame(
            SIMPLE_DATE_FORMAT.parse("2015-03-10"),
            SIMPLE_DATE_FORMAT.parse("2015-03-10"));
      assertThat(owners, not(CoreMatchers.<Owner>hasItem(hasProperty("firstName", is(equalTo("Stefan"))))));
      assertThat(owners, CoreMatchers.<Owner>hasItem(hasProperty("firstName", is(equalTo("Horst")))));
   }

   @Test
   public void testFindOwnersWithVisitWithinGivenTimeFrame_AllVisitsUntilSomeDateInPast() throws ParseException {
      final List<Owner> owners = ownerDao
         .findOwnersWithVisitWithinGivenTimeFrame(
            SIMPLE_DATE_FORMAT.parse("1990-03-10"),
            SIMPLE_DATE_FORMAT.parse("2015-03-10"));
      assertThat(owners, CoreMatchers.<Owner>hasItem(hasProperty("firstName", is(equalTo("Stefan")))));
      assertThat(owners, CoreMatchers.<Owner>hasItem(hasProperty("firstName", is(equalTo("Horst")))));
   }
}
