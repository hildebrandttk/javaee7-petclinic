package tk.hildebrandt.javaee7.petclinic.jmeter

import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

class AddVisit extends AbstractGebSamplerClient {

   @Override
   void runTest() {
      to(HelloPage)
         .toFindOwners()
         .searchForOwner("Owner${currentKey()}")
         .openDetailsForOwner('Add', "Owner${currentKey()}")
         .addVisitToPet("Bello${currentKey()}", new GregorianCalendar(2015, Calendar.APRIL, 25).time, 'Some medicine')
   }
}
