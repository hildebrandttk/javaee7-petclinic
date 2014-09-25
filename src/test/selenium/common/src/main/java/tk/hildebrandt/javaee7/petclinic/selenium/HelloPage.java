package tk.hildebrandt.javaee7.petclinic.selenium;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelloPage extends AbstractPage<HelloPage> {

   @FindBy(linkText = "Find Owners")
   private WebElement findOwnersLink;

   public void assertTitle() {
      Assert.assertEquals("Java EE 7 Petclinic", getDriver().getTitle());
   }

   protected HelloPage() {
      super("/hello.jsf");
   }

   @Override
   protected void isLoaded() throws Error {
      assertTitle();
   }

   public FindOwnersPage toFindOwners() {
      findOwnersLink.click();
      return new FindOwnersPage();
   }
}
