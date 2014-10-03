package tk.hildebrandt.javaee7.petclinic.geb.pages

class NewOwnerPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'addNewOwner') }
      firstNameInput { $('input', id:'addNewOwnerForm:firstName') }
      lastNameInput { $('input', id:'addNewOwnerForm:lastName') }
      addressInput { $('input', id:'addNewOwnerForm:address') }
      cityInput { $('input', id:'addNewOwnerForm:city') }
      telephoneInput { $('input', id:'addNewOwnerForm:telephone') }
      saveButton { $('input', id: 'addNewOwnerForm:save') }
   }

   FindOwnersResultPage addNewOwner(String firstName, String lastName, String address, String city, String telephone){
      lastNameInput.value(lastName)
      firstNameInput.value(firstName)
      addressInput.value(address)
      cityInput.value(city)
      telephoneInput.value(telephone)
      saveButton.click()
      waitForAtPage(FindOwnersResultPage)
   }
}
