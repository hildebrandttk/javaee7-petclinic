package tk.hildebrandt.javaee7.petclinic.geb.pages

class NewSpecialtiesPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'addNewSpecialty') }
      nameInput { $('input', id:'addNewSpecialtyForm:name') }
      saveButton { $('input', id: 'addNewSpecialtyForm:save') }
   }

   SpecialtiesPage addNewSpeciality(String name){
      nameInput.value(name)
      saveButton.click()
      waitForAtPage(SpecialtiesPage)
   }
}
