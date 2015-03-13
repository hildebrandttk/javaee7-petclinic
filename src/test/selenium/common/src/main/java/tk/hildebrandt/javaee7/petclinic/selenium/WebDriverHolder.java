package tk.hildebrandt.javaee7.petclinic.selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;

public class WebDriverHolder {
   private static WebDriver driver;

   public static WebDriver getDriver() {
      if (driver == null) {
         FirefoxProfile profile = new org.openqa.selenium.firefox.FirefoxProfile();
         profile.setPreference("browser.download.folderList", 2);
         profile.setPreference("browser.download.manager.showWhenStarting", false);
         profile.setPreference("browser.download.useDownloadDir", true);
         profile.setPreference("browser.helperApps.alwaysAsk.force", false);
         profile
            .setPreference("general.useragent.override", "Mozilla/5.0; rv:17.0) Gecko/20100101 Firefox/17.0 Webdriver");
         profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
            "text/csv,text/x-comma-separated-values,text/comma-separated-values,Application/csv,application/xml,text/xml,application/vnd.ms-excel");
         driver = new FirefoxDriver(profile);
         driver.manage().window().maximize();
      }
      return driver;
   }

   public static void closeDriver() {
      if (driver != null) {
         driver.quit();
         driver = null;
      }
   }

   public static void resetDriver() {
      if (driver != null) {
         driver.navigate().to("");
         if (driver.manage() != null) {
            driver.manage().deleteAllCookies();
         }
      }
   }

   public static byte[] takeScreenShot() {
      TakesScreenshot takesScreenshot = null;
      try {
         if (driver instanceof TakesScreenshot) {
            takesScreenshot = (TakesScreenshot) driver;
         } else {
            takesScreenshot = (TakesScreenshot) new Augmenter().augment(driver);
         }
         return takesScreenshot.getScreenshotAs(OutputType.BYTES);
      } finally {
         if (takesScreenshot != null) {
            ((WebDriver) takesScreenshot).close();
         }
      }
   }
}