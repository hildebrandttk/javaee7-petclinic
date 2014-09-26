package tk.hildebrandt.javaee7.petclinic.selenium;

import java.util.Arrays;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OwnersPage extends AbstractPage<OwnersPage> {

   @FindBy(id = "owners")
   private WebElement owners;

   @FindBy(id = "ownersForm:ownersTable:0:firstName")
   private WebElement firstName;

   @FindBy(id = "ownersForm:ownersTable:0:lastName")
   private WebElement lastName;

   @FindBy(id = "ownersForm:ownersTable:0:address")
   private WebElement address;

   @FindBy(id = "ownersForm:ownersTable:0:city")
   private WebElement city;

   @FindBy(id = "ownersForm:ownersTable:0:telephone")
   private WebElement telephone;

   @FindBy(id = "ownersForm:ownersTable:0:showOwner")
   private WebElement showOwner;

   protected OwnersPage() {
      super();
   }

   @Override
   protected void isLoaded() throws Error {
      Assert.assertTrue(owners.isDisplayed());
   }

   public void assertNewContentFound(String firstName,
                                     String lastName,
                                     String address,
                                     String city,
                                     String telephone) {
      Assert.assertEquals(firstName, this.firstName.getText());
      Assert.assertEquals(lastName, this.lastName.getText());
      Assert.assertEquals(address, this.address.getText());
      Assert.assertEquals(city, this.city.getText());
      Assert.assertEquals(telephone, this.telephone.getText());
      Assert.assertTrue(showOwner.isDisplayed());
   }

   public void clickShowOwner() {
      showOwner.click();
   }

   public void waitForIsLoaded() {
         waitFor(ExpectedConditions.visibilityOfAllElements(Arrays.asList(owners)));
   }
}
