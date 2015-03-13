package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Page
import tk.hildebrandt.geb.richfaces.RichFacesCalendar

class EditPetPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'editPet') }
      petNameInput { $('input', id:'editPetForm:petName') }
      petBirthDateInput { module RichFacesCalendar, $('#editPetForm\\:petBirthDate') }
      petTypeInput { $('select', id:'editPetForm:petType') }
      saveButton { $('input', id: 'editPetForm:save') }
   }

   ShowOwnerPage editPet(String petName, Date petBirthDate, String petType){
      petNameInput.value(petName)
      petBirthDateInput.value(petBirthDate)
      petTypeInput.value(petType)
      saveButton.click()
      waitForAtPage(ShowOwnerPage);
   }
}
