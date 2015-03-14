package org.woehlke.javaee7.petclinic.web;

import java.net.URL;
import java.util.Date;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.woehlke.javaee7.petclinic.web.pages.EditOwnerPage;
import org.woehlke.javaee7.petclinic.web.pages.EditPetPage;
import org.woehlke.javaee7.petclinic.web.pages.FindOwnersPage;
import org.woehlke.javaee7.petclinic.web.pages.FindOwnersResultPage;
import org.woehlke.javaee7.petclinic.web.pages.HelloPage;
import org.woehlke.javaee7.petclinic.web.pages.NewOwnerPage;
import org.woehlke.javaee7.petclinic.web.pages.NewPetPage;
import org.woehlke.javaee7.petclinic.web.pages.NewPetTypePage;
import org.woehlke.javaee7.petclinic.web.pages.NewVisitPage;
import org.woehlke.javaee7.petclinic.web.pages.PetTypesPage;
import org.woehlke.javaee7.petclinic.web.pages.ShowOwnerPage;
import static org.jboss.arquillian.graphene.Graphene.goTo;

/**
 * Created with IntelliJ IDEA. User: tw Date: 22.01.14 Time: 19:16 To change this template use File | Settings | File
 * Templates.
 */
@RunWith(Arquillian.class)
public class Test04Owner {

   @Drone
   WebDriver driver;

   @ArquillianResource
   URL deploymentUrl;

   @Page
   private HelloPage helloPage;

   @Page
   private FindOwnersPage findOwnersPage;

   @Page
   private FindOwnersResultPage findOwnersResultPage;

   @Page
   private NewOwnerPage newOwnerPage;

   @Page
   private ShowOwnerPage showOwnerPage;

   @Page
   private EditOwnerPage editOwnerPage;

   @Page
   private NewPetPage newPetPage;

   @Page
   private NewVisitPage newVisitPage;

   @Page
   private EditPetPage editPetPage;

   @Page
   private PetTypesPage petTypesPage;

   @Page
   private NewPetTypePage newPetTypePage;

   @Test
   @InSequence(1)
   @RunAsClient
   public void testOpeningHomePage() {
      goTo(HelloPage.class);
      helloPage.assertTitle();
   }

   @Test
   @InSequence(2)
   @RunAsClient
   public void testOpenFindOwnersPage() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
   }

   @Test
   @InSequence(3)
   @RunAsClient
   public void testOpenOwnersPage() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
      findOwnersPage.searchForOwner("");
      findOwnersResultPage.assertIsLoaded();
   }

   @Test
   @InSequence(4)
   @RunAsClient
   public void testOpenNewOwnerPage() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
      findOwnersPage.openNewOwnersPage();
      newOwnerPage.assertIsLoaded();
   }

   @Test
   @InSequence(5)
   @RunAsClient
   public void testOpenNewOwnerPageFromOwnersList() {
      goTo(FindOwnersPage.class)
         .assertIsLoaded()
         .searchForOwner("")
         .assertIsLoaded()
         .openNewOwnersPage()
         .assertIsLoaded();
   }

   @Test
   @InSequence(6)
   @RunAsClient
   public void testAddNewOwner() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
      findOwnersPage.searchForOwner("");
      findOwnersResultPage.assertIsLoaded();
      findOwnersResultPage.openNewOwnersPage();
      newOwnerPage.assertIsLoaded();
      newOwnerPage.addNewContent("Thomas", "Woehlke", "Schoenhauser Allee 42", "Berlin", "0301234567");
      findOwnersResultPage.assertIsLoaded();
      findOwnersResultPage.assertOwnerPresent("Thomas", "Woehlke", "Schoenhauser Allee 42", "Berlin", "0301234567");
   }

   @Test
   @InSequence(7)
   @RunAsClient
   public void testEditOwner() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
      findOwnersPage.searchForOwner("Woehlke");
      findOwnersResultPage.assertIsLoaded();
      findOwnersResultPage.clickShowOwner("Thomas", "Woehlke", "Schoenhauser Allee 42", "Berlin", "0301234567");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.clickEditOwner();
      editOwnerPage.assertPageIsLoaded();
      editOwnerPage.editContent("Willy", "Wombel", "Elbchaussee 242", "Hamburg", "0401234567");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.assertContent("Willy", "Wombel", "Elbchaussee 242", "Hamburg", "0401234567");
   }

   @Test
   @InSequence(8)
   @RunAsClient
   public void testAddNewPet() {
      goTo(PetTypesPage.class);
      petTypesPage.assertPageIsLoaded();
      petTypesPage.clickAddNewPetType();
      newPetTypePage.addNewContent("cat");
      petTypesPage.clickAddNewPetType();
      newPetTypePage.addNewContent("dog");
      petTypesPage.clickAddNewPetType();
      newPetTypePage.addNewContent("mouse");
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
      findOwnersPage.searchForOwner("Wombel");
      findOwnersResultPage.assertIsLoaded();
      findOwnersResultPage.clickShowOwner("Willy", "Wombel", "Elbchaussee 242", "Hamburg", "0401234567");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.clickAddNewPet();
      newPetPage.assertPageIsLoaded();
      Date birthDate1 = new Date(113, 04, 15); //15.05.2013
      Date birthDate2 = new Date(112, 07, 03); //03.08.2012
      newPetPage.setContent("Tomcat", birthDate1, "cat");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.clickAddNewPet();
      newPetPage.setContent("Bully", birthDate2, "dog");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.assertFirstPetContent("Bully", birthDate2, "dog");
      showOwnerPage.assertSecondPetContent("Tomcat", birthDate1, "cat");
   }

   @Test
   @InSequence(9)
   @RunAsClient
   public void testEditPet() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
      findOwnersPage.searchForOwner("Wombel");
      findOwnersResultPage.assertIsLoaded();
      findOwnersResultPage.clickShowOwner("Willy", "Wombel", "Elbchaussee 242", "Hamburg", "0401234567");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.clickEditFirstPet();
      editPetPage.assertPageIsLoaded();
      Date birthDate = new Date(110, 05, 01); //01.06.2010
      editPetPage.setContent("Speedy", birthDate, "mouse");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.assertFirstPetContent("Speedy", birthDate, "mouse");
   }

   @Test
   @InSequence(10)
   @RunAsClient
   public void testAddVisitToFirstPet() {
      goTo(FindOwnersPage.class);
      findOwnersPage.assertIsLoaded();
      findOwnersPage.searchForOwner("Wombel");
      findOwnersResultPage.assertIsLoaded();
      findOwnersResultPage.clickShowOwner("Willy", "Wombel", "Elbchaussee 242", "Hamburg", "0401234567");
      showOwnerPage.assertPageIsLoaded();
      showOwnerPage.addVisitToFirstPet();
      newVisitPage.assertPageIsLoaded();
      Date birthDate = new Date(110, 05, 01); //01.06.2010
      newVisitPage.assertOwnerContent("Willy", "Wombel");
      newVisitPage.assertPetContent("Speedy", birthDate, "mouse");
      Date visitDate = new Date(114, 01, 16); //16.01.2014
      newVisitPage.setNewContent(visitDate, "get milk");
      showOwnerPage.assertFirstVisitToFirstPet(visitDate, "get milk");
   }
}
