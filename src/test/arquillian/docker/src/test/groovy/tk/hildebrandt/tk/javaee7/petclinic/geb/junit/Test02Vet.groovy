package tk.hildebrandt.tk.javaee7.petclinic.geb.junit

import geb.junit4.GebTest
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.junit.InSequence
import org.junit.Test
import org.junit.runner.RunWith
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.SpecialtiesPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.VeterinariansPage

@RunWith(Arquillian)
class Test02Vet extends GebTest {

   @Test
   @InSequence(1)
   @RunAsClient
   public void testOpeningHomePage() {
      to(HelloPage)
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testOpeningVetPage() {
      to(VeterinariansPage)
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testNewVetPage() {
      to(VeterinariansPage)
         .assertVeterinarianNotPresent('Hans', 'Wurst')
         .openNewVeterinarianPage()
         .addNewVeterinarian('Hans', 'Wurst')
         .assertVeterinarianPresent('Hans', 'Wurst')
         .toHome()
   }

   @Test
   @InSequence(4)
   @RunAsClient
   public void testEditVetPage() {
      to(VeterinariansPage)
         .openEditVeterinarianPage('to', 'Edit')
         .editVeterinarian('edited', 'ToEdit')
         .assertVeterinarianPresent('edited', 'ToEdit')
         .assertVeterinarianNotPresent('to', 'Edit')
         .toHome()
   }

   @Test
   @InSequence(5)
   @RunAsClient
   public void testDeleteVetPage() {
      to(VeterinariansPage)
         .assertVeterinarianPresent('to', 'Delete')
         .deleteVeterinarian('to', 'Delete')
         .assertVeterinarianNotPresent('to', 'Delete')
         .toHome()
   }

   @Test
   @InSequence(6)
   @RunAsClient
   public void testNewVetPageWithSpecialties() {
      to(SpecialtiesPage.class)
         .openNewSpecialityPage()
         .addNewSpeciality('dentist')
         .openNewSpecialityPage()
         .addNewSpeciality('anesthetist')
         .openNewSpecialityPage()
         .addNewSpeciality('radiology')
         .toVeterinarians()
         .openNewVeterinarianPage()
         .addNewVeterinarianAllSpecialties('Thomas', 'Woehlke')
         .assertVeterinarianPresent('Thomas', 'Woehlke', 'anesthetist', 'dentist', 'radiology')
         .toHome()
   }

   @Test
   @InSequence(7)
   @RunAsClient
   public void testEditVetPageWithSpecialties() {
      to(VeterinariansPage.class)
         .assertVeterinarianPresent('to', 'Edit2', 'anesthetist', 'dentist', 'radiology')
         .openEditVeterinarianPage('to', 'Edit2')
         .removeAllSpecialties()
         .assertVeterinarianPresent('to', 'Edit2', 'none')
         .toHome()
   }

   /**
    * Test for #24 new specialties not visible in veterinarians editmode
    */
   @Test
   @InSequence(8)
   @RunAsClient
   public void testEditVetPageWithNewSpecialties() {
      to(VeterinariansPage.class)
         .toSpecialties()
         .openNewSpecialityPage()
         .addNewSpeciality("hero")
         .toVeterinarians()
         .openEditVeterinarianPage('to', 'Edit3')
         .addAllSpecialties()
         .assertVeterinarianPresent('to', 'Edit3', 'anesthetist', 'dentist', 'radiology', 'hero')
         .toHome()
   }
}
