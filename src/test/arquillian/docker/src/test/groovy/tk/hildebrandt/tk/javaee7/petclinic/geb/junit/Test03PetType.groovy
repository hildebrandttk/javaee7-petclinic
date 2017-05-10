package tk.hildebrandt.tk.javaee7.petclinic.geb.junit

import geb.junit4.GebTest
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.junit.InSequence
import org.junit.Test
import org.junit.runner.RunWith
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.PetTypesPage

@RunWith(Arquillian)
class Test03PetType extends GebTest {

   @Test
   @InSequence(1)
   @RunAsClient
   public void testOpeningHomePage() {
      to(HelloPage)
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testOpeningPetTypesPage() {
      to(PetTypesPage)
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testNewPetTypePage() {
      to(PetTypesPage)
         .assertPetTypeNotPresent('mouse')
         .openNewPetTypePage()
         .addNewPetType('mouse')
         .assertPetTypePresent('mouse');
   }

   @Test
   @InSequence(4)
   @RunAsClient
   public void testEditPetTypePage() {
      to(PetTypesPage)
         .assertPetTypePresent('toEdit')
         .openEditPetTypePage('toEdit')
         .editPetType('edited')
         .assertPetTypePresent('edited')
         .assertPetTypeNotPresent('toEdit');
   }

   @Test
   @InSequence(5)
   @RunAsClient
   public void testDeletePetTypePage() {
      to(HelloPage.class)
         .toPetTypes()
         .assertPetTypePresent('toDelete')
         .deletePetType('toDelete')
         .assertPetTypeNotPresent('toDelete');
   }
}
