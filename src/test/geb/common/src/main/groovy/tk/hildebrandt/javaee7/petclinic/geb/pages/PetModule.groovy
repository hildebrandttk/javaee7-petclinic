package tk.hildebrandt.javaee7.petclinic.geb.pages
import geb.Module
import org.junit.Assert
import java.text.SimpleDateFormat

class PetModule extends Module {
   static content = {
      masterDataCell { $('td:first-child') }
      masterDataCellText { masterDataCell.text() }
      masterDataRows { masterDataCell.find('dl dd span', it) }
      name { masterDataRows(0).text() }
      birthDate { new SimpleDateFormat('MMM dd,yyyy', Locale.US).parse(masterDataRows(1).text()) }
      petType { masterDataRows(2).text() }
      visitsAndActionsCell { masterDataCell.next() }
      visitsList { moduleList VisitModule, $('table.table-condensed>tbody>tr') }
      editLink { visitsAndActionsCell.find('a', text: 'Edit Pet') }
      addVisitLink { visitsAndActionsCell.find('a', text: 'Add Visit') }
   }

   EditPetPage openEdit() {
      editLink.click();
      waitFor(message: 'EditPetPage is not present') { browser.isAt(EditPetPage) }
      return browser.page as EditPetPage
   }

   NewVisitPage openAddVisit() {
      addVisitLink.click();
      waitFor(message: 'NewVisitPage is not present') { browser.isAt(NewVisitPage) }
      return browser.page as NewVisitPage
   }

   ShowOwnerPage assertVisit(final Date visitDate, final String visitDescription) {
      for (VisitModule visit  : visitsList) {
         def date = visit.visitDate
         def description = visit.visitDescription
         if(visitDate == date
            && visitDescription == description){
            browser.isAt(ShowOwnerPage)
            return browser.page as ShowOwnerPage
         }
      }
      Assert.fail("no visit at $visitDate with description $visitDescription")
      return null
   }
}
