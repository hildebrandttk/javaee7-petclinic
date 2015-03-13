package org.woehlke.javaee7.petclinic.web.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPetTypePage {

    @FindBy(id="editPetType")
    private WebElement editPetType;

    @FindBy(id="editPetTypeForm:name")
    private WebElement name;

    @FindBy(id="editPetTypeForm:save")
    private WebElement save;


    public void assertPageIsLoaded() {
        Assert.assertTrue(editPetType.isDisplayed());
    }

    public void editContent(String content) {
        name.clear();
        name.sendKeys(content);
        save.click();
    }
}
