package tk.hildebrandt.javaee7.petclinic.selenium;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertTrue;

public class FindOwnersResultPage extends FindOwnersPage<FindOwnersResultPage> {

   @FindBy(id = "ownersForm:getNewOwnerForm")
   private WebElement createNewOwnerLink;

   @FindBy(id = "ownersForm:ownersTable")
   private WebElement ownersTable;

   public NewOwnerPage clickNewOwner() {
      createNewOwnerLink.click();
      return new NewOwnerPage();
   }

   @Override
   public FindOwnersResultPage assertIsLoaded() {
      Assert.assertTrue(ownersTable.isDisplayed());
      return this;
   }

   FindOwnersResultPage assertOwnerPresent(String firstName, String lastName, String address, String city,
                                           String telephone) {
      boolean hasResult = false;
      for (WebElement rows : ownersTable.findElements(By.tagName("tr"))) {
         final List<WebElement> columns = rows.findElements(By.tagName("td"));
         if(columns.size()>3){
            if(columns.get(0).getText().equals(firstName+" "+lastName)
               && columns.get(1).getText().equals(address)
               && columns.get(2).getText().equals(city)
               && columns.get(3).getText().equals(telephone)){
               hasResult = true;
            }
         }
      }

      assertTrue(hasResult);
      return this;
   }

   public FindOwnersResultPage waitForIsLoaded() {
      waitFor(ExpectedConditions.visibilityOfAllElements(Arrays.asList(ownersTable)));
      return this;
   }
}
