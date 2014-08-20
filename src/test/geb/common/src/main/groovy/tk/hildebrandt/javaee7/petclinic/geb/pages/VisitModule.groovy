package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Module
import java.text.SimpleDateFormat

class VisitModule extends Module {

   static content = {
      cell(required: false ) { $("td", it) }
      visitDate { new SimpleDateFormat('MMM dd,yyyy', Locale.US).parse(cell(0).text()) }
      visitDescription { cell(1).text() }
   }
}
