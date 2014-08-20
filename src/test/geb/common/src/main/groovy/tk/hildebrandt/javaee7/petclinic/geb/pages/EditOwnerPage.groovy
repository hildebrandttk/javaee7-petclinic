package tk.hildebrandt.javaee7.petclinic.geb.pages

class EditOwnerPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'editOwner') }
      firstNameInput { $('input', id:'editOwnerForm:firstName') }
      lastNameInput { $('input', id:'editOwnerForm:lastName') }
      addressInput { $('input', id:'editOwnerForm:address') }
      cityInput { $('input', id:'editOwnerForm:city') }
      telephoneInput { $('input', id:'editOwnerForm:telephone') }
      saveButton { $('input', id: 'editOwnerForm:save') }
   }

   ShowOwnerPage editOwner(String firstName, String lastName, String address, String city, String telephone){
      lastNameInput.value(lastName)
      firstNameInput.value(firstName)
      addressInput.value(address)
      cityInput.value(city)
      telephoneInput.value(telephone)
      saveButton.click()
      waitFor {browser.isAt(ShowOwnerPage)}
      return browser.page as ShowOwnerPage
   }
}
