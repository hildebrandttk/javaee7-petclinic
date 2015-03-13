package org.woehlke.javaee7.petclinic.web.pages;

import java.util.List;

import org.jboss.arquillian.graphene.page.Page;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA. User: tw Date: 26.01.14 Time: 22:24 To change this template use File | Settings | File
 * Templates.
 */
public class FindOwnersResultPage extends AbstractFindOwnersPage<FindOwnersResultPage> {

   @FindBy(id = "owners")
   private WebElement owners;

   @FindBy(id = "ownersForm:ownersTable")
   private OwnersTableFragment ownerTable;

   @Page
   private ShowOwnerPage showOwnerPage;

   @Override
   public FindOwnersResultPage assertIsLoaded() {
      Assert.assertTrue(owners.isDisplayed());
      return this;
   }

   public FindOwnersResultPage assertOwnerPresent(String firstName, String lastName, String address, String city,
                                                  String telephone) {
      final List<OwnersTableRowFragment> rowsByParameters =
         ownerTable.findRowsByParameters(firstName, lastName, address, city, telephone);
      if(rowsByParameters.isEmpty()){
         fail(
            "No owner " + firstName + " " + lastName + " (" + address + ", " + city + ", " + telephone + ") present.");
         return null;
      }else{
         return this;
      }
   }

   public ShowOwnerPage clickShowOwner(){
      fail();
      return null;
   }
   public ShowOwnerPage clickShowOwner(String firstName, String lastName, String address, String city,
                                       String telephone) {
      final List<OwnersTableRowFragment> rowsByParameters =
         ownerTable.findRowsByParameters(firstName, lastName, address, city, telephone);
      if(rowsByParameters.isEmpty()){
         fail(
            "No owner " + firstName + " " + lastName + " (" + address + ", " + city + ", " + telephone + ") present.");
         return null;
      }else{
         rowsByParameters.get(0).openDetails();
         return showOwnerPage;
      }
   }

   public FindOwnersResultPage searchForOwner(String name) {
      searchForOwnerInternal(name);
      return this;
   }
}
