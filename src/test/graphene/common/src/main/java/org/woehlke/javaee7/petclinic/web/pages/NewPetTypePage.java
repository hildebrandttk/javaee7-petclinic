package org.woehlke.javaee7.petclinic.web.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPetTypePage {

    @FindBy(id="addNewPetType")
    private WebElement addNewPetType;

    @FindBy(id="addNewPetTypeForm:name")
    private WebElement name;

    @FindBy(id="addNewPetTypeForm:save")
    private WebElement save;

    public void assertPageIsLoaded() {
        Assert.assertTrue(addNewPetType.isDisplayed());
    }

    public void addNewContent(String content) {
        name.clear();
        name.sendKeys(content);
        save.click();
    }
}
