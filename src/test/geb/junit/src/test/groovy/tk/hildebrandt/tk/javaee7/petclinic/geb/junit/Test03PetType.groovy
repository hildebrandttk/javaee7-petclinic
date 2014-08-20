package tk.hildebrandt.tk.javaee7.petclinic.geb.junit
import geb.junit4.GebTest
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.junit.InSequence
import org.junit.Test
import org.junit.runner.RunWith
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

@RunWith(Arquillian)
class Test03PetType extends GebTest {

   @Test
   @InSequence(1)
   @RunAsClient
   public void testNewPetTypePage() {
      to(HelloPage.class)
         .toPetTypes()
         .assertPetTypeNotPresent('mouse')
         .openNewPetTypePage()
         .addNewPetType('mouse')
         .assertPetTypePresent('mouse');
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testEditPetTypePage() {
      to(HelloPage.class)
         .toPetTypes()
         .assertPetTypePresent('mouse')
         .openEditPetTypePage('mouse')
         .editPetType('elephant')
         .assertPetTypePresent('elephant')
         .assertPetTypeNotPresent('mouse');
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testDeletePetTypePage() {
      to(HelloPage.class)
         .toPetTypes()
         .assertPetTypePresent('elephant')
         .deletePetType('elephant')
         .assertPetTypeNotPresent('elephant');
   }
}
