package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class EditVet extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage.class)
         .toVeterinarians()
         .openEditVeterinarianPage('Create', "Vet${currentKey()}")
         .addAllSpecialties()
   }
}
