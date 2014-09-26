package tk.hildebrandt

import cucumber.api.java.After
import geb.Browser
import geb.Configuration
import geb.ConfigurationLoader

class GebStepDefinitions {
   String gebConfEnv = null
   String gebConfScript = null

   private Browser _browser

   Configuration createConf() {
      new ConfigurationLoader(gebConfEnv, System.properties, new GroovyClassLoader(getClass().classLoader)).getConf(gebConfScript)
   }

   Browser createBrowser() {
      new Browser(createConf())
   }

   Browser getBrowser() {
      if (_browser == null) {
         _browser = createBrowser()
      }
      _browser
   }

   @After
   void resetBrowser() {
      if (_browser?.config?.autoClearCookies) {
         _browser.clearCookiesQuietly()
      }
      _browser = null
   }

   def methodMissing(String name, args) {
      getBrowser()."$name"(* args)
   }

   def propertyMissing(String name) {
      getBrowser()."$name"
   }

   def propertyMissing(String name, value) {
      getBrowser()."$name" = value
   }
}
