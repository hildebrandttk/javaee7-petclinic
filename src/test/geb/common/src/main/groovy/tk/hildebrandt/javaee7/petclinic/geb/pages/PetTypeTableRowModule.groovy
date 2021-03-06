package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Module

class PetTypeTableRowModule extends Module {
   static content = {
      cell { $("td", it) }
      name { cell(0).text() }
      editLink { cell(1).find('a') }
      deleteLink { cell(2).find('a') }
   }

   EditPetTypePage edit() {
      editLink.click()
      waitFor(message: 'EditPetTypePage is not present') { browser.isAt(EditPetTypePage) }
      return browser.page as EditPetTypePage
   }

   PetTypesPage delete() {
      deleteLink.click()
      waitFor(message: 'PetTypesPage is not present') { browser.isAt(PetTypesPage) }
      return browser.page as PetTypesPage
   }
}
