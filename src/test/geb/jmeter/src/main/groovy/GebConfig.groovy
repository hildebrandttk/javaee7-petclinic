import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities

reportsDir = "target/test-reports/geb"

cacheDriverPerThread = true

baseUrl = 'http://localhost:8080/javaee7-petclinic-1.3-SNAPSHOT/'
driver = {
   def mod = Counter.COUNTER.getAndIncrement() % 25
   if (mod == 0) {
      return createFirefoxDriver()
   } else {
      return createPhantomJsDriver()
   }
}

private FirefoxDriver createFirefoxDriver() {
   def FirefoxProfile profile = new FirefoxProfile();

   profile.setPreference("general.useragent.override", "Mozilla/5.0; rv:17.0) Gecko/20100101 Firefox/17.0 Webdriver");

   def ffDriver = new FirefoxDriver(profile)
   ffDriver.manage().window().maximize()
   return ffDriver
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
   firefox {
      driver = {
         return createFirefoxDriver()
      }
   }
   phantomjs {
      driver = {
         return createPhantomJsDriver()
      }
   }
   chrome {
      driver = {
         createChromeDriver()
      }
   }
}

private void createChromeDriver() {
   final chromeDriver = new ChromeDriver()
   chromeDriver.manage().window().maximize()
   chromeDriver
}

private PhantomJSDriver createPhantomJsDriver() {
   final capabilities = new DesiredCapabilities()
   capabilities.setCapability("phantomjs.page.settings.userAgent",
      "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.17 Safari/537.36")
   final phantomJSDriver = new PhantomJSDriver(capabilities)
   phantomJSDriver.manage().window().setSize(new Dimension(1028, 768))
   return phantomJSDriver
}
