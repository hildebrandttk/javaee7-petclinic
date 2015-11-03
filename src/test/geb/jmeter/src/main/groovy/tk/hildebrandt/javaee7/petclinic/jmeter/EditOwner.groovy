package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class EditOwner extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage)
         .toFindOwners()
         .searchForOwner("Owner${currentKey()}")
         .openDetailsForOwner('Add', "Owner${currentKey()}")
         .openEditOwner()
      .editOwner('Edit', "Owner${currentKey()}", "Neu", "Neustadt", '4234')
   }
}
