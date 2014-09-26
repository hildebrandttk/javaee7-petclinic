package tk.hildebrandt.javaee7.petclinic.selenium;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class Test04Owner {

   @AfterClass
   public static void closeBrowser() {
      WebDriverHolder.closeDriver();
   }

   @After
   public void resetBrowser() {
      WebDriverHolder.resetDriver();
   }

   @Test
   @InSequence(1)
   @RunAsClient
   public void testOpeningHomePage() {
      HelloPage helloPage = new HelloPage();
      helloPage.get();
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testOpenFindOwnersPage() {
      final FindOwnersPage findOwnersPage = new FindOwnersPage();
      findOwnersPage.get();
      findOwnersPage.assertPageIsLoaded();
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testOpenOwnersResultPage() {
      final FindOwnersPage findOwnersPage = new FindOwnersPage();
      findOwnersPage.get();
      findOwnersPage.assertPageIsLoaded();
      FindOwnersResultPage ownersResultPage = findOwnersPage.searchForOwner("");
      ownersResultPage.isLoaded();
   }

   @Test
   @InSequence(4)
   @RunAsClient
   public void testOpenNewOwnerPage() {
      final FindOwnersPage findOwnersPage = new FindOwnersPage();
      findOwnersPage.get();
      findOwnersPage.assertPageIsLoaded();
      NewOwnerPage newOwnerPage = findOwnersPage.openNewOwnersPage();
      newOwnerPage.assertPageIsLoaded();
   }

   @Test
   @InSequence(5)
   @RunAsClient
   public void testOpenNewOwnerPageFromOwnersList() {
      final FindOwnersPage findOwnersPage = new FindOwnersPage();
      findOwnersPage.get();
      findOwnersPage.assertPageIsLoaded();
      FindOwnersResultPage ownersPage = findOwnersPage.searchForOwner("");
      ownersPage.isLoaded();
      NewOwnerPage newOwnerPage = ownersPage.clickNewOwner();
      newOwnerPage.assertPageIsLoaded();
   }
//
//   @Test
//   @InSequence(6)
//   @RunAsClient
//   @Ignore("Problems with data setup")
//   public void testAddNewOwner() {
//      goTo(FindOwnersPage.class);
//      findOwnersPage.assertPageIsLoaded();
//      findOwnersPage.clickSearch();
//      ownersPage.assertPageIsLoaded();
//      ownersPage.openNewOwnersPage();
//      newOwnerPage.assertPageIsLoaded();
//      newOwnerPage.addNewContent("Thomas","Woehlke","Schoenhauser Allee 42","Berlin","03012345678");
//      ownersPage.assertPageIsLoaded();
//      ownersPage.assertNewContentFound("Thomas","Woehlke","Schoenhauser Allee 42","Berlin","03012345678");
//   }
//
//   @Test
//   @InSequence(7)
//   @RunAsClient
//   @Ignore("Problems with data setup")
//   public void testEditOwner() {
//      goTo(FindOwnersPage.class);
//      findOwnersPage.assertPageIsLoaded();
//      findOwnersPage.clickSearch();
//      ownersPage.assertPageIsLoaded();
//      ownersPage.clickShowOwner();
//      showOwnerPage.assertPageIsLoaded();
//      showOwnerPage.clickEditOwner();
//      editOwnerPage.assertPageIsLoaded();
//      editOwnerPage.editContent("Willy","Wombel","Elbchaussee 242","Hamburg","04012345678");
//      showOwnerPage.assertPageIsLoaded();
//      showOwnerPage.assertContent("Willy","Wombel","Elbchaussee 242","Hamburg","04012345678");
//   }
//
//   @Test
//   @InSequence(8)
//   @RunAsClient
//   @Ignore("Problems with data setup")
//   public void testAddNewPet() {
//      goTo(PetTypesPage.class);
//      petTypesPage.assertPageIsLoaded();
//      petTypesPage.clickAddNewPetType();
//      newPetTypePage.addNewContent("cat");
//      petTypesPage.clickAddNewPetType();
//      newPetTypePage.addNewContent("dog");
//      petTypesPage.clickAddNewPetType();
//      newPetTypePage.addNewContent("mouse");
//      goTo(FindOwnersPage.class);
//      findOwnersPage.assertPageIsLoaded();
//      findOwnersPage.clickSearch();
//      ownersPage.assertPageIsLoaded();
//      ownersPage.clickShowOwner();
//      showOwnerPage.assertPageIsLoaded();
//      showOwnerPage.clickAddNewPet();
//      newPetPage.assertPageIsLoaded();
//      Date birthDate1 = new Date(113, 04, 15); //15.05.2013
//      Date birthDate2 = new Date(112, 07, 03); //03.08.2012
//      newPetPage.setContent("Tomcat", birthDate1, "cat");
//      showOwnerPage.clickAddNewPet();
//      newPetPage.setContent("Bully", birthDate2, "dog");
//      showOwnerPage.assertFirstPetContent("Bully", birthDate2, "dog");
//      showOwnerPage.assertSecondPetContent("Tomcat", birthDate1, "cat");
//   }
//
//   @Test
//   @InSequence(9)
//   @RunAsClient
//   @Ignore("Problems with data setup")
//   public void testEditPet() {
//      goTo(FindOwnersPage.class);
//      findOwnersPage.assertPageIsLoaded();
//      findOwnersPage.clickSearch();
//      ownersPage.assertPageIsLoaded();
//      ownersPage.clickShowOwner();
//      showOwnerPage.assertPageIsLoaded();
//      showOwnerPage.clickEditFirstPet();
//      editPetPage.assertPageIsLoaded();
//      Date birthDate = new Date(110, 05, 01); //01.06.2010
//      editPetPage.setContent("Speedy", birthDate, "mouse");
//      showOwnerPage.assertFirstPetContent("Speedy", birthDate, "mouse");
//   }
//
//   @Test
//   @InSequence(10)
//   @RunAsClient
//   @Ignore("Problems with data setup")
//   public void testAddVisitToFirstPet() {
//      goTo(FindOwnersPage.class);
//      findOwnersPage.assertPageIsLoaded();
//      findOwnersPage.clickSearch();
//      ownersPage.assertPageIsLoaded();
//      ownersPage.clickShowOwner();
//      showOwnerPage.assertPageIsLoaded();
//      showOwnerPage.addVisitToFirstPet();
//      newVisitPage.assertPageIsLoaded();
//      Date birthDate = new Date(110, 05, 01); //01.06.2010
//      newVisitPage.assertOwnerContent("Willy","Wombel");
//      newVisitPage.assertPetContent("Speedy", birthDate, "mouse");
//      Date visitDate = new Date(114, 01, 16); //16.01.2014
//      newVisitPage.setNewContent(visitDate,"get milk");
//      showOwnerPage.assertFirstVisitToFirstPet(visitDate,"get milk");
//   }
//
}
