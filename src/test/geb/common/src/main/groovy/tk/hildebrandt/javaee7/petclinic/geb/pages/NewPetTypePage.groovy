package tk.hildebrandt.javaee7.petclinic.geb.pages

class NewPetTypePage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'addNewPetType') }
      nameInput { $('input', id:'addNewPetTypeForm:name') }
      saveButton { $('input', id: 'addNewPetTypeForm:save') }
   }

   PetTypesPage addNewPetType(String name){
      nameInput.value(name)
      saveButton.click()
      waitForAtPage(PetTypesPage)
   }
}
