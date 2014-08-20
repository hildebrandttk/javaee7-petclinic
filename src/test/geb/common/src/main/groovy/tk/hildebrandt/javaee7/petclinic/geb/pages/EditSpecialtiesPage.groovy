package tk.hildebrandt.javaee7.petclinic.geb.pages

class EditSpecialtiesPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'editSpecialty') }
      nameInput { $('input', id:'editSpecialtyForm:name') }
      saveButton { $('input', id: 'editSpecialtyForm:save') }
   }

   SpecialtiesPage editSpeciality(String name){
      nameInput.value(name)
      saveButton.click()
      waitFor {browser.isAt(SpecialtiesPage)}
      return browser.page as SpecialtiesPage
   }
}
