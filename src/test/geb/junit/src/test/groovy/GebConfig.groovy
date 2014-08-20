import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities

reportsDir = "target/test-reports/geb"

cacheDriverPerThread=true

baseUrl='http://localhost:8080/javaee7-petclinic-1.3-SNAPSHOT/'
driver = {
   def FirefoxProfile profile = new FirefoxProfile();

   installFirebug(profile)

   profile.setPreference("browser.download.folderList", 2);
   profile.setPreference("browser.download.manager.showWhenStarting", false);
   profile.setPreference("browser.download.useDownloadDir", true)
   profile.setPreference("browser.helperApps.alwaysAsk.force", false)
   profile.setPreference("general.useragent.override", "Mozilla/5.0; rv:17.0) Gecko/20100101 Firefox/17.0 Webdriver");

   profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,text/x-comma-separated-values,text/comma-separated-values,Application/csv,application/xml,text/xml,application/vnd.ms-excel");

   def ffDriver = new FirefoxDriver(profile)
   ffDriver.manage().window().maximize()
   return ffDriver
}

protected void installFirebug(FirefoxProfile profile) {
   URL dir_url = ClassLoader.getResource("/firebug-1.12.0-fx.xpi");
   if (dir_url) {
      File file = new File(dir_url.toURI());
      profile.addExtension(file);
      profile.setPreference("extensions.firebug.currentVersion", "1.12.0");
   }
}

waiting {
   timeout = 10
   retryInterval = 0.5
   presets {
      test {
         timeout = 3
         retryInterval = 0.5
      }
   }
}

environments {
   'phantomjs' {
      driver = {
         final capabilities = new DesiredCapabilities()
         capabilities.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.17 Safari/537.36")
         final phantomJSDriver = new PhantomJSDriver(capabilities)
         phantomJSDriver.manage().window().setSize(new Dimension(1028, 768))
         return phantomJSDriver
      }
   }
   chrome {
      driver = {
         final chromeDriver = new ChromeDriver()
         chromeDriver.manage().window().maximize()
         chromeDriver
      }
   }
}
