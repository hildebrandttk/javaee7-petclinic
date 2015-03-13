package org.woehlke.javaee7.petclinic.web.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewSpecialtiesPage {

    @FindBy(id="addNewSpecialty")
    private WebElement addNewSpecialty;

    @FindBy(id="addNewSpecialtyForm:name")
    private WebElement name;

    @FindBy(id="addNewSpecialtyForm:save")
    private WebElement save;

    public void assertPageIsLoaded(){
        Assert.assertTrue(addNewSpecialty.isDisplayed());
    }

    public void addNewContent(String content) {
        name.clear();
        name.sendKeys(content);
        save.click();
    }
}
