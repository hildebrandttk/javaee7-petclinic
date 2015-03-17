package tk.hildebrandt.tk.javaee7.petclinic.geb.junit

import geb.junit4.GebTest
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.junit.InSequence
import org.junit.Test
import org.junit.runner.RunWith
import tk.hildebrandt.javaee7.petclinic.geb.pages.FindOwnersPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.PetTypesPage

@RunWith(Arquillian)
class Test04Owner extends GebTest {

   @Test
   @InSequence(1)
   @RunAsClient
   public void testOpeningHomePage() {
      to(HelloPage)
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testOpenFindOwnersPage() {
      to(FindOwnersPage)
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testSearch() {
      to(FindOwnersPage)
         .searchForOwner('')
   }

   @Test
   @InSequence(4)
   @RunAsClient
   public void testOpenNewOwnerPage() {
      to(HelloPage)
         .toFindOwners()
         .openNewOwnersPage()
   }

   @Test
   @InSequence(5)
   @RunAsClient
   public void testOpenNewOwnerPageFromOwnersList() {
      to(HelloPage)
         .toFindOwners()
         .searchForOwner('')
         .openNewOwnersPage()
   }

   @Test
   @InSequence(6)
   @RunAsClient
   public void testAddNewOwner() {
      to(HelloPage)
         .toFindOwners()
         .searchForOwner('')
         .openNewOwnersPage()
         .addNewOwner("Thomas", "Woehlke", "Schoenhauser Allee 42", "Berlin", "0301234567")
         .assertOwnerPresent("Thomas", "Woehlke", "Schoenhauser Allee 42", "Berlin", "0301234567");
   }

   @Test
   @InSequence(7)
   @RunAsClient
   public void testEditOwner() {
      to(HelloPage)
         .toFindOwners()
         .searchForOwner('Woehlke Thomas')
         .openDetailsForOwner("Thomas", "Woehlke")
         .openEditOwner()
         .editOwner("Willy", "Wombel", "Elbchaussee 242", "Hamburg", "0401234567")
         .assertOwnerData("Willy", "Wombel", "Elbchaussee 242", "Hamburg", "0401234567");
   }

   @Test
   @InSequence(8)
   @RunAsClient
   public void testAddNewPet() {
      Date birthDate1 = new Date(113, 04, 15); //15.05.2013
      Date birthDate2 = new Date(112, 07, 03); //03.08.2012
      to(PetTypesPage)
         .openNewPetTypePage()
         .addNewPetType('cat')
         .openNewPetTypePage()
         .addNewPetType('dog')
         .openNewPetTypePage()
         .addNewPetType('mouse')
         .toFindOwners()
         .searchForOwner('Wombel')
         .openDetailsForOwner('Willy', 'Wombel')
         .openAddNewPet()
         .addNewPet("Tomcat", birthDate1, "cat")
         .openAddNewPet()
         .addNewPet("Bully", birthDate2, "dog")
         .assertPetContent("Bully", birthDate2, "dog")
         .assertPetContent("Tomcat", birthDate1, "cat");
   }

   @Test
   @InSequence(9)
   @RunAsClient
   public void testEditPet() {
      Date birthDate = new Date(110, 05, 01); //01.06.2010
      to(HelloPage.class)
         .toFindOwners()
         .searchForOwner('Wombel')
         .openDetailsForOwner('Willy', 'Wombel')
         .openEditPet('Tomcat')
         .editPet("Speedy", birthDate, "mouse")
         .assertPetContent("Speedy", birthDate, "mouse");
   }

   @Test
   @InSequence(10)
   @RunAsClient
   public void testAddVisitToFirstPet() {
      Date birthDate = new Date(110, 05, 01); //01.06.2010
      Date visitDate = new Date(114, 01, 16); //16.01.2014
      to(HelloPage.class)
         .toFindOwners()
         .searchForOwner('Wombel')
         .openDetailsForOwner('Willy', 'Wombel')
         .assertPetContent("Speedy", birthDate, "mouse")
         .openAddVisit('Speedy')
         .assertOwnerContent("Willy", "Wombel")
         .assertPetContent("Speedy", birthDate, "mouse")
         .visit(visitDate, "get milk")
         .assertVisitToPet("Speedy", visitDate,"get milk");
   }
}
