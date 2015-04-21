package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Module

class SpecialtiesTableRowModule extends Module {
   static content = {
      cell { $("td", it) }
      name { cell(0).text() }
      editLink { $('a.editLink') }
      deleteLink { $('a.deleteLink') }
   }

   EditSpecialtiesPage edit() {
      editLink.click()
      waitFor(message: 'EditSpecialtiesPage is not present') { browser.isAt(EditSpecialtiesPage) }
      return browser.page as EditSpecialtiesPage
   }

   SpecialtiesPage delete() {
      deleteLink.click()
      waitFor(message: 'SpecialtiesPage is not present') { browser.isAt(SpecialtiesPage) }
      return browser.page as SpecialtiesPage
   }
}
