package tk.hildebrandt.javaee7.petclinic.selenium;

import cucumber.api.Scenario;
import cucumber.api.java.After;
public class WebDriverHolderCucumberLifeCycle {

   @After(order = 1)
   public static void resetDriver() {
      WebDriverHolder.closeDriver();
   }

   @After
   public void takeScreenShot(Scenario scenario) {
      if(scenario.isFailed()) {
         scenario.embed(WebDriverHolder.takeScreenShot(), "image/png");
      }
   }
}