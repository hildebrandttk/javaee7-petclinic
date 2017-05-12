import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

reportsDir = "target/test-reports/geb"

cacheDriverPerThread = true

baseUrl = 'http://localhost:18080/javaee7-petclinic-1.4.0/'

driver = {
   final chromeDriver = new ChromeDriver()
   chromeDriver.manage().window().maximize()
   chromeDriver
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
         capabilities.setCapability("phantomjs.page.settings.userAgent",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.17 Safari/537.36")
         final phantomJSDriver = new PhantomJSDriver(capabilities)
         phantomJSDriver.manage().window().setSize(new Dimension(1028, 768))
         return phantomJSDriver
      }
   }
   gridff {
      driver = { new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox()) }
      baseUrl = 'http://petclinic:8080/javaee7-petclinic-1.4.0/'
   }
   gridchrome {
      driver = { new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()) }
      baseUrl = 'http://petclinic:8080/javaee7-petclinic-1.4.0/'
   }
}
