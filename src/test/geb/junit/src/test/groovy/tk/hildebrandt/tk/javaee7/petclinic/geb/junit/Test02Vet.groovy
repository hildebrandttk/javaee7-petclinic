package tk.hildebrandt.tk.javaee7.petclinic.geb.junit
import geb.junit4.GebTest
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.junit.InSequence
import org.junit.Test
import org.junit.runner.RunWith
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

@RunWith(Arquillian)
class Test02Vet extends GebTest {

   @Test
   @InSequence(1)
   @RunAsClient
   public void testNewVetPage() {
      to(HelloPage)
         .toVeterinarians()
         .assertVeterinarianNotPresent('Hans', 'Wurst')
         .openNewVeterinarianPage()
         .addNewVeterinarian('Hans', 'Wurst')
         .assertVeterinarianPresent('Hans', 'Wurst')
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testEditVetPage() {
      to(HelloPage)
         .toVeterinarians()
         .assertVeterinarianPresent('Hans', 'Wurst')
         .openEditVeterinarianPage('Hans', 'Wurst')
         .editVeterinarian('Hans2', 'Wurst2')
         .assertVeterinarianPresent('Hans2', 'Wurst2')
         .assertVeterinarianNotPresent('Hans', 'Wurst')
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testDeleteVetPage() {
      to(HelloPage)
         .toVeterinarians()
         .assertVeterinarianPresent('Hans2', 'Wurst2')
         .deleteVeterinarian('Hans2', 'Wurst2')
         .assertVeterinarianNotPresent('Hans2', 'Wurst2')
   }

   @Test
   @InSequence(4)
   @RunAsClient
   public void testNewVetPageWithSpecialties() {
      to(HelloPage.class)
         .toSpecialties()
         .openNewSpecialityPage()
         .addNewSpeciality('dentist')
         .openNewSpecialityPage()
         .addNewSpeciality('anesthetist')
         .openNewSpecialityPage()
         .addNewSpeciality('radiology')
         .toVeterinarians()
         .openNewVeterinarianPage()
         .addNewVeterinarianAllSpecialties('Thomas', 'Woehlke')
         .assertVeterinarianPresent('Thomas', 'Woehlke', 'anesthetist', 'dentist', 'radiology');
   }

   @Test
   @InSequence(5)
   @RunAsClient
   public void testEditVetPageWithSpecialties() {
      to(HelloPage.class)
         .toVeterinarians()
         .assertVeterinarianPresent('Thomas', 'Woehlke', 'anesthetist', 'dentist', 'radiology')
         .openEditVeterinarianPage('Thomas', 'Woehlke')
         .removeAllSpecialties()
         .assertVeterinarianPresent('Thomas', 'Woehlke', 'none')
   }
}
