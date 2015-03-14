package tk.hildebrandt.tk

import org.junit.Test

class PowerAssert {
   @Test
   void showPowerAssert(){
      def sda = "sda"
      assert 1 == 1 && "ad${sda}sd" == 'adsdasd' && new Object() == new Object()
   }
}
