package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class AddOwner extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage.class)
         .toFindOwners()
         .openNewOwnersPage()
         .addNewOwner('Add', "Owner${currentKey()}", 'Some Street', 'ACity', '0190666666')
   }
}
