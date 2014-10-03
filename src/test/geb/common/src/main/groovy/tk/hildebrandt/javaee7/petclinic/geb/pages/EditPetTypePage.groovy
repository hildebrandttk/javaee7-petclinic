package tk.hildebrandt.javaee7.petclinic.geb.pages

class EditPetTypePage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'editPetType') }
      nameInput { $('input', id:'editPetTypeForm:name') }
      saveButton { $('input', id: 'editPetTypeForm:save') }
   }

   PetTypesPage editPetType(String name){
      nameInput.value(name)
      saveButton.click()
      return waitForAtPage(PetTypesPage)
   }
}
