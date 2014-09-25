package tk.hildebrandt.javaee7.petclinic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public abstract class AbstractPage<T extends LoadableComponent<T>> extends LoadableComponent<T> {
   protected static final String BASE_URL = "http://localhost:8080/petclinic-all/";
   private static WebDriver driver;
   private final String pageUrl;

   protected AbstractPage() {
      this(null);
   }

   protected AbstractPage(String pageUrl) {
      this.pageUrl = pageUrl;
      driver = WebDriverHolder.getDriver();
      PageFactory.initElements(driver, this);
   }

   @Override
   protected final void load() {
      getDriver().get(BASE_URL + pageUrl);
   }

   @Override
   protected void isLoaded() throws Error {
      assertTrue(getDriver().getCurrentUrl().endsWith(pageUrl));
   }

   protected static WebDriver getDriver() {
      return driver;
   }

   protected void waitFor(final ExpectedCondition<?> expectedCondition){
      Wait<WebDriver> wait = new WebDriverWait(driver, 3);
      wait.until(expectedCondition);
   }
}
