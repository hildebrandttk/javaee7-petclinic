package tk.hildebrandt.tk.javaee7.petclinic.geb.junit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.PersistenceTest;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@UsingDataSet("UiTests.yml")
@PersistenceTest
@Cleanup(strategy = CleanupStrategy.USED_TABLES_ONLY)
public class Test00CleanUp {
   @PersistenceContext(unitName = "javaee7petclinic")
   private EntityManager entityManager;

   @Test
   public void cleanupDb() {
   }
}
