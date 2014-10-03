package org.woehlke.javaee7.petclinic.web.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.richfaces.fragment.pickList.RichFacesPickList;

public class NewVetPage {

    @FindBy(id="addNewVeterinarian")
    private WebElement addNewVeterinarian;

    @FindBy(id="addNewVeterinarianForm:firstName")
    private WebElement firstName;

    @FindBy(id="addNewVeterinarianForm:lastName")
    private WebElement lastName;

    @FindBy(id="addNewVeterinarianForm:save")
    private WebElement save;

    @FindBy(id="addNewVeterinarianForm:selectedSpecialtiesPickList")
    private RichFacesPickList pickList;

    public void assertPageIsLoaded() {
        Assert.assertTrue(addNewVeterinarian.isDisplayed());
    }

    public void addNewContent(String firstName,String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        save.click();
    }

    public void addNewContentWithAllSpecialties(String firstName, String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        this.pickList.addAll();
        save.click();
    }
}
