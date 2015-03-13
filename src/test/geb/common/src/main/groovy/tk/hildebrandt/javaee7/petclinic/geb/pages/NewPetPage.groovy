package tk.hildebrandt.javaee7.petclinic.geb.pages

import tk.hildebrandt.geb.richfaces.RichFacesCalendar

class NewPetPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'addNewPet') }
      petNameInput { $('input', id:'addNewPetForm:petName') }
      petBirthDateInput { module RichFacesCalendar, $('div', id:'addNewPetForm:petBirthDate') }
      petTypeInput { $('select', id:'addNewPetForm:petType') }
      addButton { $('input', id: 'addNewPetForm:add') }
   }

   ShowOwnerPage addNewPet(String petName, Date petBirthDate, String petType){
      petNameInput.value(petName)
      petBirthDateInput.value(petBirthDate)
      petTypeInput.value(petType)
      addButton.click()
      waitForAtPage(ShowOwnerPage)
   }
}
