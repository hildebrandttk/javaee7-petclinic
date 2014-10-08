package org.woehlke.javaee7.petclinic.web;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.woehlke.javaee7.petclinic.web.pages.HelloPage;
import org.woehlke.javaee7.petclinic.web.pages.SpecialtiesPage;
import org.woehlke.javaee7.petclinic.web.pages.VetsPage;
import static org.jboss.arquillian.graphene.Graphene.goTo;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA. User: tw Date: 21.01.14 Time: 21:54 To change this template use File | Settings | File
 * Templates.
 */
@RunWith(Arquillian.class)
@Ignore
public class TestDroneOnly {

   @Drone
   WebDriver driver;

   @ArquillianResource
   URL deploymentUrl;

   @Test
   @InSequence(1)
   @RunAsClient
   public void testOpeningHomePage() {
      driver.get(deploymentUrl + "/hello.jsf");
      assertEquals("Java EE 7 Petclinic", driver.getTitle());
   }
}
