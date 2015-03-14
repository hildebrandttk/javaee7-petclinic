package tk.hildebrandt.geb.richfaces

import geb.Module
import geb.Page

class RichFacesPickList extends Module {
   static content = {
      sourceValues { $('ol.source li').text() }
      buttonAddAll { $('button.rf-pick-add-all') }
      buttonAdd { $('button.rf-pick-add') }
      buttonRemove { $('button.rf-pick-rem') }
      buttonRemoveAll { $('button.rf-pick-rem-all') }
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
