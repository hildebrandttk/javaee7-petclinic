package tk.hildebrandt.javaee7.petclinic.geb.pages

import tk.hildebrandt.geb.richfaces.RichFacesPickList

class EditVeterinarianPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'editVeterinarian') }
      firstNameInput { $('input', id:'editVeterinarianForm:firstName') }
      lastNameInput { $('input', id:'editVeterinarianForm:lastName') }
      specialtiesPickList { module RichFacesPickList, $('div', id: 'editVeterinarianForm:selectedSpecialtiesPickList') }
      saveButton { $('input', id: 'editVeterinarianForm:save') }
   }

   VeterinariansPage addAllSpecialties() {
      specialtiesPickList.addAll()
      saveButton.click()
      waitFor {browser.isAt(VeterinariansPage)}
      return browser.page as VeterinariansPage
   }

   VeterinariansPage removeAllSpecialties() {
      specialtiesPickList.removeAll()
      saveButton.click()
      waitFor {browser.isAt(VeterinariansPage)}
      return browser.page as VeterinariansPage
   }

   VeterinariansPage editVeterinarian(final String firstName, final String lastName){
      firstNameInput.value(firstName)
      lastNameInput.value(lastName)
      saveButton.click()
      waitFor {browser.isAt(VeterinariansPage)}
      return browser.page as VeterinariansPage
   }
}
