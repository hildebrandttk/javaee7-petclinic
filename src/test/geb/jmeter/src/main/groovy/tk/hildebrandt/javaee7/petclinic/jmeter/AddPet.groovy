package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class AddPet extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage)
         .toFindOwners()
         .searchForOwner("Owner${currentKey()}")
         .openDetailsForOwner('Add', "Owner${currentKey()}")
         .openAddNewPet()
         .addNewPet("Bello${currentKey()}", new GregorianCalendar(2012, Calendar.APRIL, 25).time, "Chi${currentKey()}")
   }
}
