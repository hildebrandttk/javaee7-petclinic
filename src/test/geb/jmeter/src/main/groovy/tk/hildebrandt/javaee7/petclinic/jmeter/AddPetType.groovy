package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class AddPetType extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage)
         .toPetTypes()
         .openNewPetTypePage()
         .addNewPetType("Chi${currentKey()}")
   }
}
