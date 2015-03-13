package tk.hildebrandt.javaee7.petclinic.geb.pages

import tk.hildebrandt.geb.richfaces.RichFacesPickList

class NewVeterinarianPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'addNewVeterinarian') }
      lastNameInput { $('input', id:'addNewVeterinarianForm:lastName') }
      firstNameInput { $('input', id:'addNewVeterinarianForm:firstName') }
      specialtiesPickList { module RichFacesPickList, $('div', id: 'addNewVeterinarianForm:selectedSpecialtiesPickList') }
      saveButton { $('input', id: 'addNewVeterinarianForm:save') }
   }

   VeterinariansPage addNewVeterinarian(String firstName, String lastName){
      lastNameInput.value(lastName)
      firstNameInput.value(firstName)
      saveButton.click()
      waitForAtPage(VeterinariansPage)
   }

   VeterinariansPage addNewVeterinarianAllSpecialties(String firstName, String lastName) {
      lastNameInput.value(lastName)
      firstNameInput.value(firstName)
      specialtiesPickList.addAll();
      saveButton.click()
      waitForAtPage(VeterinariansPage)
   }
}
