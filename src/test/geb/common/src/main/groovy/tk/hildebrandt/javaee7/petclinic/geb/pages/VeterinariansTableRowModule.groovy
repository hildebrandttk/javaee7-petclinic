package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Module

class VeterinariansTableRowModule extends Module {
   static content = {
      cell(required: false ) { $("td", it) }
      firstName(required: false ) { cell(0).text() }
      lastName(required: false ) { cell(1).text() }
      specialties(required: false ) { cell(2).text() }
      editLink { cell(3).find('a') }
      deleteLink { cell(4).find('a') }
   }

   EditVeterinarianPage edit() {
      editLink.click()
      waitFor { browser.isAt(EditVeterinarianPage) }
      return browser.page as EditVeterinarianPage
   }

   VeterinariansPage delete() {
      deleteLink.click()
      waitFor { browser.isAt(VeterinariansPage) }
      return browser.page as VeterinariansPage
   }
}
