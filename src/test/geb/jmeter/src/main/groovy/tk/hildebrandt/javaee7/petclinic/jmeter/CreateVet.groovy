package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class CreateVet extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage)
         .toVeterinarians()
         .openNewVeterinarianPage()
         .addNewVeterinarian('Create', "Vet${generateKey()}")
   }
}
