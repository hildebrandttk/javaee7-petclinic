package tk.hildebrandt.javaee7.petclinic.geb.pages

class FindOwnersPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'findOwners') }
      nameInput { $('input', type:'text') }
      searchButton { $('input', type: 'submit') }
      addNewOwnerType { $('a', text: 'Add New Owner') }
   }

   FindOwnersResultPage searchForOwner(String name){
      nameInput.value(name)
      searchButton.click()
      waitFor {browser.isAt(FindOwnersResultPage)}
      return browser.page as FindOwnersResultPage
   }

   NewOwnerPage openNewOwnersPage() {
      addNewOwnerType.click()
      waitFor { browser.isAt(NewOwnerPage) }
      return browser.page as NewOwnerPage
   }
}
