package tk.hildebrandt.javaee7.petclinic.jmeter

import geb.Browser
import geb.Configuration
import geb.ConfigurationLoader
import geb.Page
import geb.PageChangeListener
import org.apache.jmeter.assertions.AssertionResult
import org.apache.jmeter.config.Arguments
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext
import org.apache.jmeter.samplers.SampleResult
import java.nio.charset.Charset
import java.security.MessageDigest

abstract class AbstractGebSamplerClient extends AbstractJavaSamplerClient implements Serializable, PageChangeListener {
   private static final ThreadLocal<String> THREAD_IDENTIFIER = new ThreadLocal<>()
   private static final MessageDigest messageDigest = MessageDigest.getInstance("SHA")
   private Stack<SampleResult> resultStack = new Stack<SampleResult>();
   private JavaSamplerContext javaSamplerContext

   @Override
   Arguments getDefaultParameters() {
      return new Arguments()
   }

   @Override
   final SampleResult runTest(final JavaSamplerContext javaSamplerContext) {
      this.javaSamplerContext = javaSamplerContext
      def result = new SampleResult()
      resultStack.push(result)
      result.setSampleLabel(getClass().getSimpleName())
      result.sampleStart()
      try {
         result.setSuccessful(true)
         runTest()
      } catch (Exception | AssertionError e) {
         while (!resultStack.empty()) {
            resultStack.peek().setSuccessful(false)
            def assertionResult = new AssertionResult()
            assertionResult.setError(true)
            assertionResult.setFailureMessage(e.getMessage())
            resultStack.pop().addAssertionResult(assertionResult)
         }
         result.setSuccessful(false)
         def assertionResult1 = new AssertionResult()
         assertionResult1.setError(true)
         assertionResult1.setFailureMessage(e.getMessage())
         result.addAssertionResult(assertionResult1)
      } finally {
         while (!resultStack.empty()) {
            resultStack.pop().sampleEnd()
         }
      }
      return result
   }

   abstract void runTest()

   @Override
   void setupTest(final JavaSamplerContext context) {
      getBrowser()
   }

   @Override
   void teardownTest(final JavaSamplerContext context) {
      resetBrowser()
   }

   String gebConfEnv = null

   String gebConfScript = null

   private Browser _browser

   Configuration createConf() {
      new ConfigurationLoader(gebConfEnv, System.properties, new GroovyClassLoader(getClass().classLoader)).
         getConf(gebConfScript)
   }

   Browser createBrowser() {
      Browser browser = new Browser(createConf())
      browser.registerPageChangeListener(this)
      browser
   }

   Browser getBrowser() {
      if (_browser == null) {
         _browser = createBrowser()
      }
      _browser
   }

   void resetBrowser() {
      if (_browser?.config?.autoClearCookies) {
         _browser.close()
         _browser = null
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

   @Override
   void pageWillChange(Browser browser, Page oldPage, Page newPage) {
      if (oldPage && resultStack.peek().getSampleLabel(false) == oldPage.getPageUrl()) {
         resultStack.pop().sampleEnd()
      }
      if (newPage) {
         def result = new SampleResult()
         result.setSuccessful(true)
         resultStack.peek().addSubResult(result)
         resultStack.push(result)
         result.setSampleLabel(newPage.getPageUrl())
         result.sampleStart()
      }
   }

   static String generateKey() {
      def digest = messageDigest.digest(
         "-${Thread.currentThread().getId()}-${System.currentTimeMillis()}".getBytes(Charset.defaultCharset()))
      StringBuilder builder = new StringBuilder()
      for (int i = 0; i < 16; i++) {
         char a = 'A'
         char b = a + Math.abs(digest[i] % 26) as char
         builder.append(b)
      }
      THREAD_IDENTIFIER.set(builder.toString())
      currentKey()
   }

   static String currentKey() {
      THREAD_IDENTIFIER.get()
   }
}
