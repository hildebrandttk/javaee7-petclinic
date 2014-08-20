package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Module

class OwnersTableRowModule extends Module {
   static content = {
      cell(required: false ) { $("td", it) }
      editOwnerLink(required: false ) { cell(0).find('a') }
      name(required: false ) { cell(0).text() }
      address(required: false ) { cell(1).text() }
      city(required: false ) { cell(2).text() }
      telephone(required: false ) { cell(3).text() }
   }

   ShowOwnerPage openDetails() {
      editOwnerLink.click()
      waitFor { browser.isAt(ShowOwnerPage) }
      return browser.page as ShowOwnerPage
   }
}
