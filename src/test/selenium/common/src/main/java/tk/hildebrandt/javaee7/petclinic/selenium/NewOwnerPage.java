package tk.hildebrandt.javaee7.petclinic.selenium;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewOwnerPage extends AbstractPage<NewOwnerPage> {

    @FindBy(id="addNewOwner")
    private WebElement addNewOwner;

    @FindBy(id="addNewOwnerForm:firstName")
    private WebElement firstName;

    @FindBy(id="addNewOwnerForm:lastName")
    private WebElement lastName;

    @FindBy(id="addNewOwnerForm:address")
    private WebElement address;

    @FindBy(id="addNewOwnerForm:city")
    private WebElement city;

    @FindBy(id="addNewOwnerForm:telephone")
    private WebElement telephone;

    @FindBy(id="addNewOwnerForm:save")
    private WebElement save;

   protected NewOwnerPage() {
      super();
   }

   public void assertPageIsLoaded() {
        Assert.assertTrue(addNewOwner.isDisplayed());
    }

    public void addNewOwner(String firstName,
                              String lastName,
                              String address,
                              String city,
                              String telephone) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.address.sendKeys(address);
        this.city.sendKeys(city);
        this.telephone.sendKeys(telephone);
        this.save.click();
    }
}
