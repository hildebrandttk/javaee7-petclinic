package org.woehlke.javaee7.petclinic.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OwnersTableFragment {

   @Root
   private WebElement root;

   @FindBy(css = "tbody.rf-dt-b > tr")
   private List<OwnersTableRowFragment> rows;

   public List<OwnersTableRowFragment> findRowsByParameters(String firstName, String lastName, String address,
                                                            String city, String telephone) {
      List<OwnersTableRowFragment> matchingRows = new ArrayList<>();
      for (OwnersTableRowFragment row : rows) {
         if (row.getLastName().equals(lastName) && row.getFirstName().equals(firstName)
            && row.getAddress().equals(address) && row.getCity().equals(city) && row.getTelephone().equals(telephone)) {
            matchingRows.add(row);
         }
      }
      return matchingRows;
   }
}
