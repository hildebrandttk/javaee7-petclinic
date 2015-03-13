package tk.hildebrandt.tk.javaee7.petclinic.geb.junit
import geb.junit4.GebTest
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.junit.InSequence
import org.junit.Test
import org.junit.runner.RunWith
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.SpecialtiesPage
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@RunWith(Arquillian)
class Test01Specialties extends GebTest {

   @PersistenceContext(unitName = "javaee7petclinic")
   private EntityManager entityManager;

   @Test
   @InSequence(1)
   @RunAsClient
   public void testOpeningHomePage() {
      to(HelloPage)
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testOpeningSpecialtiesPage() {
      to(SpecialtiesPage)
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testCreateNewSpeciality() {
      to(SpecialtiesPage)
         .openNewSpecialityPage()
         .addNewSpeciality('dentist')
         .assertSpecialityPresent('dentist');
   }

   @Test
   @InSequence(4)
   @RunAsClient
   public void testEditSpecialty() {
      to(SpecialtiesPage)
         .openEditSpecialtyPage('dentist')
         .editSpeciality('newDentist')
         .assertSpecialityPresent('newDentist');
   }

   @Test
   @InSequence(5)
   @RunAsClient
   public void testDeleteSpecialty() {
      to(SpecialtiesPage)
         .deleteSpecialty('newDentist')
         .assertSpecialityNotPresent('newDentist');
   }
}
