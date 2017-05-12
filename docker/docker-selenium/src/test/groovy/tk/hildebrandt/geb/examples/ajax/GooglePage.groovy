package tk.hildebrandt.geb.examples.ajax

import geb.Page

class GooglePage extends Page {
   static url = "https://www.google.de/"
   static content = {
      queryField { $('input', name: 'q') }
      suggestionsBox { $('ul', class: 'sbsb_b') }
      suggestions { suggestionsBox.find('li')*.text() }
   }

   public GooglePage enterQuery(String query) {
      queryField.value(query)
      waitFor { suggestionsBox.displayed }
      this
   }

   public void assertSuggestionPresent(String suggestion){
      assert suggestions.contains(suggestion)
   }
}
