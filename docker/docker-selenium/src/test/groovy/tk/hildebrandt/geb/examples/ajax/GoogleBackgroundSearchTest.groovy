package tk.hildebrandt.geb.examples.ajax

import geb.junit4.GebTest
import org.junit.Test

class GoogleBackgroundSearchTest extends GebTest {

   @Test
   public void test(){
      to(GooglePage)
         .enterQuery("Wikipedia")
         .assertSuggestionPresent("wikipedia")
   }

}
