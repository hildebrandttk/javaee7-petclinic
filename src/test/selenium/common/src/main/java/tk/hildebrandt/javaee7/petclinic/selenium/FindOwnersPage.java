package tk.hildebrandt.javaee7.petclinic.selenium;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindOwnersPage extends AbstractPage<FindOwnersPage> {

   @FindBy(id = "findOwners")
   private WebElement findOwners;

   @FindBy(id = "findOwnersForm:search")
   private WebElement search;

   @FindBy(linkText = "Add New Owner")
   private WebElement addNewOwnerLink;

   @FindBy(css = "input[type='text']")
   private WebElement nameInput;

   protected FindOwnersPage() {
      super("findOwners.jsf");
   }

   public void assertPageIsLoaded() throws Error {
      Assert.assertTrue(findOwners.isDisplayed());
   }

   public OwnersPage clickSearch() {
      search.click();
      return new OwnersPage();
   }

   public NewOwnerPage openNewOwnersPage() {
      addNewOwnerLink.click();
      return new NewOwnerPage();
   }

   public FindOwnersResultPage searchForOwner(String name) {
      nameInput.sendKeys(name);
      search.click();
      return new FindOwnersResultPage();
   }
}
