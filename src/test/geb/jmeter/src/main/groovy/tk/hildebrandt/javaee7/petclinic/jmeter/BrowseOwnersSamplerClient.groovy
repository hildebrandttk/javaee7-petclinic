package tk.hildebrandt.javaee7.petclinic.jmeter

import org.apache.jmeter.assertions.AssertionResult
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext
import org.apache.jmeter.samplers.SampleResult
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class BrowseOwnersSamplerClient extends AbstractGebSamplerClient {

   @Override
   SampleResult runTest(final JavaSamplerContext javaSamplerContext) {
      def result = new SampleResult()
      result.setSampleLabel("Search")
      result.sampleStart()
      try {
         result.setSuccessful(true)
         to(HelloPage)
            .toFindOwners()
            .searchForOwner('Hans')
      } catch (Exception e){
         result.setSuccessful(false)
         def result1 = new AssertionResult()
         result1.setError(true)
         result1.setFailureMessage(e.getMessage())
         result.addAssertionResult(result1)
      } catch (AssertionError e){
         result.setSuccessful(false)
         def result1 = new AssertionResult()
         result1.setFailure(true)
         result1.setFailureMessage(e.getMessage())
         result.addAssertionResult(result1)
      } finally {
         result.sampleEnd()
      }
      return result
   }
}
