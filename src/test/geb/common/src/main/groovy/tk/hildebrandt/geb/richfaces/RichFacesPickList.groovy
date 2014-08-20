package tk.hildebrandt.geb.richfaces

import geb.Module
import geb.Page

class RichFacesPickList extends Module {
   static content = {
      sourceValues { $('ol.source li').text() }
      buttonAddAll { $('button.btn-add-all') }
      buttonAdd { $('button.btn-add') }
      buttonRemove { $('button.btn-remove') }
      buttonRemoveAll { $('button.btn-remove-all') }
   }

   public <P extends Page> P addAll(P source) {
      buttonAddAll.click()
      return source
   }

   public <P extends Page> P removeAll(P source) {
      buttonRemoveAll.click()
      return source
   }
}
