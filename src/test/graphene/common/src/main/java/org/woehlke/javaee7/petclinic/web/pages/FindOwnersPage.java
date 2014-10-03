package org.woehlke.javaee7.petclinic.web.pages;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.enricher.PageObjectEnricher;
import org.jboss.arquillian.graphene.page.Location;
import org.jboss.arquillian.graphene.page.Page;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("findOwners.jsf")
public class FindOwnersPage<T extends FindOwnersPage<T>> extends AbstractFindOwnersPage<T> {

   @Page
   private FindOwnersResultPage findOwnersResultPage;

   public FindOwnersResultPage searchForOwner(String name) {
      searchForOwnerInternal(name);
      return findOwnersResultPage;
   }
}
