package org.woehlke.javaee7.petclinic.web.pages;

import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OwnersTableRowFragment {

   @Root
   private WebElement root;

   @FindBy(css = ".firstName")
   private WebElement firstName;

   @FindBy(css = ".lastName")
   private WebElement lastName;

   @FindBy(css = ".address")
   private WebElement address;

   @FindBy(css = ".city")
   private WebElement city;

   @FindBy(css = ".telephone")
   private WebElement telephone;

   public String getFirstName() {
      return firstName.getText();
   }

   public String getLastName() {
      return lastName.getText();
   }

   public String getAddress() {
      return address.getText();
   }

   public String getCity() {
      return city.getText();
   }

   public String getTelephone() {
      return telephone.getText();
   }

   public void openDetails() {
      firstName.click();
   }
//   cell(required: false ) { $("td", it) }
//   firstName(required: false ) { cell(0).text() }
//   lastName(required: false ) { cell(1).text() }
//   specialties(required: false ) { cell(2).text() }
//   editLink { cell(3).find('a') }
//   deleteLink { cell(4).find('a') }
}
