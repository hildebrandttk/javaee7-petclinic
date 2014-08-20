package tk.hildebrandt.javaee7.petclinic.jmeter
import geb.Browser
import geb.Configuration
import geb.ConfigurationLoader
import org.apache.jmeter.config.Arguments
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext

abstract class AbstractGebSamplerClient extends AbstractJavaSamplerClient implements Serializable {

   @Override
   Arguments getDefaultParameters() {
      return new Arguments()
   }

   @Override
   void setupTest(final JavaSamplerContext context) {
      to(HelloPage)
   }

   @Override
   void teardownTest(final JavaSamplerContext context) {
      resetBrowser()
   }

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

   void resetBrowser() {
      if (_browser?.config?.autoClearCookies) {
         _browser.clearCookiesQuietly()
      }
   }

   def methodMissing(String name, args) {
      getBrowser()."$name"(*args)
   }

   def propertyMissing(String name) {
      getBrowser()."$name"
   }

   def propertyMissing(String name, value) {
      getBrowser()."$name" = value
   }
}
