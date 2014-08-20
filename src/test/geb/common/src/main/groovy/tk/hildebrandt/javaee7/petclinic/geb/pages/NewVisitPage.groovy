package tk.hildebrandt.javaee7.petclinic.geb.pages

import tk.hildebrandt.geb.richfaces.RichFacesCalendar
import java.text.SimpleDateFormat

class NewVisitPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'addVisit') }
      visitDateInput { module RichFacesCalendar, $('#addVisitForm\\:visitDate') }
      visitDescriptionInput { $('input', id:'addVisitForm:visitDescription') }
      ownerFirstName { $('#addVisitForm\\:ownerFirstName').text() }
      ownerLastName { $('#addVisitForm\\:ownerLastName').text() }
      petName { $('#addVisitForm\\:petName').text() }
      petBirthDate {  new SimpleDateFormat('MMM dd,yyyy', Locale.US).parse($('#addVisitForm\\:petBirthDate').text()) }
      petType {  $('#addVisitForm\\:petType').text() }
      saveButton { $('input', id: 'addVisitForm:save') }
   }

   ShowOwnerPage visit(Date visitDate, String visitDescription){
      visitDateInput.value(visitDate)
      visitDescriptionInput.value(visitDescription)
      saveButton.click()
      waitFor {browser.isAt(ShowOwnerPage)}
      return browser.page as ShowOwnerPage
   }

   NewVisitPage assertOwnerContent(final String firstName, final String lastName) {
      assert firstName == ownerFirstName
      assert lastName == ownerLastName
      return this
   }

   NewVisitPage assertPetContent(final String name, final Date birthDate, final String type) {
      assert name == petName
      assert birthDate == petBirthDate
      assert type == petType
      return this
   }
}
