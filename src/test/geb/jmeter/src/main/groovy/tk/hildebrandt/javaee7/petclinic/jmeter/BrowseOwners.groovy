package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class BrowseOwners extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage.class)
         .toFindOwners()
         .searchForOwner("Owner${currentKey()}")
         .assertOwnerPresent('Add', "Owner${currentKey()}", 'Some Street', 'ACity', '0190666666')
   }
}
