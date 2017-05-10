package tk.hildebrandt.javaee7.petclinic.geb.pages

import org.junit.Assert

class ShowOwnerPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'showOwner') }
      firstNameOutput { $('span', id: 'showOwnerForm:firstName').text() }
      lastNameOutput { $('span', id: 'showOwnerForm:lastName').text() }
      addressOutput { $('span', id: 'showOwnerForm:address').text() }
      cityOutput { $('span', id: 'showOwnerForm:city').text() }
      telephoneOutput { $('span', id: 'showOwnerForm:telephone').text() }
      petList { moduleList PetModule, $('#showOwnerForm\\:petsAndVisitsTable>tbody>tr') }
      editButton { $('input', id: 'showOwnerForm:edit') }
      newPetButton { $('input', id: 'showOwnerForm:addPet') }
   }

   EditOwnerPage openEditOwner() {
      editButton.click()
      waitForAtPage(EditOwnerPage)
   }

   ShowOwnerPage assertOwnerData(String firstName, String lastName, String address, String city, String telephone) {
      assert (firstName == firstNameOutput && lastName == lastNameOutput && address == addressOutput
         && city == cityOutput && telephone == telephoneOutput)
      this
   }

   NewPetPage openAddNewPet() {
      newPetButton.click()
      waitForAtPage(NewPetPage)
   }

   ShowOwnerPage assertPetContent(final String petName, final Date birthDate, final String petType) {
      for (PetModule petModule : petList) {
         if(petName == petModule.name
            && birthDate == petModule.birthDate
            && petType == petModule.petType){
            return this
         }
      }
      Assert.fail("No pet $petName, $birthDate, $petType")
      return null
   }

   EditPetPage openEditPet(final String petName) {
      for (PetModule petModule : petList) {
         if(petName == petModule.name){
            return petModule.openEdit()
         }
      }
      Assert.fail("No pet $petName")
      return null
   }

   NewVisitPage openAddVisit(final String petName) {
      for (PetModule petModule : petList) {
         if(petName == petModule.name){
            return petModule.openAddVisit()
         }
      }
      Assert.fail("No pet $petName")
      return null
   }

   ShowOwnerPage addVisitToPet(final String petName, Date visitDate, String visitDescription) {
      return openAddVisit(petName)
         .visit(visitDate, visitDescription)
   }

   ShowOwnerPage assertVisitToPet(final String petName, final Date visitDate, final String visitDescription) {
      for (PetModule petModule : petList) {
         if(petName == petModule.name){
            return petModule.assertVisit(visitDate, visitDescription)
         }
      }
      Assert.fail("No pet $petName with visit on $visitDate and description $visitDescription")
      return null;
   }
}
