package org.woehlke.javaee7.petclinic.web.pages;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("petTypes.jsf")
public class PetTypesPage {

    @FindBy(id="petTypes")
    private WebElement petTypes;

    @FindBy(id="petTypesForm:getNewPetTypeForm")
    private WebElement getNewPetTypeForm;

    @FindBy(id="petTypesForm:petTypesTable:0:name")
    private WebElement nameInTable;

    @FindBy(id="petTypesForm:petTypesTable:0:edit")
    private WebElement editInTable;

    @FindBy(id="petTypesForm:petTypesTable:0:delete")
    private WebElement deleteInTable;

    public void assertPageIsLoaded() {
        Graphene.waitModel().until().element(petTypes).is().visible();
        Assert.assertTrue(petTypes.isDisplayed());
    }

    public void clickAddNewPetType() {
        Graphene.guardHttp(getNewPetTypeForm).click();
    }

    public void assertNewContentFound(String content) {
        Assert.assertEquals(content, nameInTable.getText());
    }

    public void clickEditSpecialty() {
        Graphene.guardHttp(editInTable).click();
    }

    public void assertEditedContentFound(String content) {
        Assert.assertEquals(content, nameInTable.getText());
    }

    public void clickDeleteSpecialty() {
        Graphene.guardHttp(deleteInTable).click();
    }

    public void assertDeletedContentNotFound() {
        boolean isDeleted = false;
        try {
            Assert.assertEquals(null,nameInTable);
        } catch (NoSuchElementException elementException) {
            isDeleted = true;
        }
        Assert.assertTrue(isDeleted);
    }
}
