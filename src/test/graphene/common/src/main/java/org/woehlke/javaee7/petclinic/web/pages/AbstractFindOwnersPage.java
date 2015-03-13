package org.woehlke.javaee7.petclinic.web.pages;

import org.jboss.arquillian.graphene.page.Page;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractFindOwnersPage<T extends AbstractFindOwnersPage<T>> {
   @FindBy(id = "findOwners")
   private WebElement findOwners;
   @FindBy(id = "findOwnersForm:search")
   private WebElement search;
   @FindBy(linkText = "Add New Owner")
   private WebElement addNewOwnerLink;
   @FindBy(css = "input[type='text']")
   private WebElement nameInput;
   @Page
   private NewOwnerPage newOwnerPage;

   public T assertIsLoaded() {
      Assert.assertTrue(findOwners.isDisplayed());
      return (T) this;
   }

   public NewOwnerPage openNewOwnersPage() {
      addNewOwnerLink.click();
      return newOwnerPage;
   }

   protected void searchForOwnerInternal(String name) {
      nameInput.clear();
      nameInput.sendKeys(name);
      search.click();
   }

   public abstract FindOwnersResultPage searchForOwner(final String s);
}
