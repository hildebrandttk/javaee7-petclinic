package org.woehlke.javaee7.petclinic.web.pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

@Location("hello.jsf")
public class HelloPage {

    @Drone
    private WebDriver driver;

    public void assertTitle(){
        Assert.assertEquals("Java EE 7 Petclinic",driver.getTitle());
    }
}
