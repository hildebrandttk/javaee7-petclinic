package org.woehlke.javaee7.petclinic.web;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.woehlke.javaee7.petclinic.web.pages.EditSpecialtiesPage;
import org.woehlke.javaee7.petclinic.web.pages.HelloPage;
import org.woehlke.javaee7.petclinic.web.pages.NewSpecialtiesPage;
import org.woehlke.javaee7.petclinic.web.pages.SpecialtiesPage;
import static org.jboss.arquillian.graphene.Graphene.goTo;


/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 19.01.14
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class Test01Specialties {

    @Drone
    private WebDriver driver;

    @ArquillianResource
    private URL deploymentUrl;

    @Page
    private HelloPage helloPage;

    @Page
    private SpecialtiesPage specialtiesPage;

    @Page
    private NewSpecialtiesPage newSpecialtiesPage;

    @Page
    private EditSpecialtiesPage editSpecialtiesPage;

    @Test
    @InSequence(1)
    public void testOpeningHomePage() {
        goTo(HelloPage.class);
        helloPage.assertTitle();
    }


    @Test
    @InSequence(2)
    public void testOpeningSpecialtiesPage() {
        goTo(SpecialtiesPage.class);
        specialtiesPage.assertPageIsLoaded();
    }


    @Test
    @InSequence(3)
    public void testNewSpecialtyPage() {
        goTo(SpecialtiesPage.class);
        specialtiesPage.assertPageIsLoaded();
        specialtiesPage.clickAddNewSpecialty();
        newSpecialtiesPage.assertPageIsLoaded();
        newSpecialtiesPage.addNewContent("dentist");
        specialtiesPage.assertPageIsLoaded();
        specialtiesPage.assertNewContentFound("dentist");
    }


    @Test
    @InSequence(4)
    public void testEditSpecialtyPage() {
        goTo(SpecialtiesPage.class);
        specialtiesPage.assertPageIsLoaded();
        specialtiesPage.clickEditSpecialty();
        editSpecialtiesPage.assertPageIsLoaded();
        editSpecialtiesPage.editContent("specialist");
        specialtiesPage.assertPageIsLoaded();
        specialtiesPage.assertEditedContentFound("specialist");
    }

    @Test
    @InSequence(5)
    public void testDeleteSpecialtyPage() {
        goTo(SpecialtiesPage.class);
        specialtiesPage.assertPageIsLoaded();
        specialtiesPage.clickDeleteSpecialty();
        specialtiesPage.assertPageIsLoaded();
        specialtiesPage.assertDeletedContentNotFound();
    }


}
