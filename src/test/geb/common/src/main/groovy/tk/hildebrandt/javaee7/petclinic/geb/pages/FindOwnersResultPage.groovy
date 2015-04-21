package tk.hildebrandt.javaee7.petclinic.geb.pages

import static org.junit.Assert.fail

class FindOwnersResultPage extends FindOwnersPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'owners') }
      ownersList { $('table', id: 'ownersForm:ownersTable') }
      rowsInTable { moduleList OwnersTableRowModule, $('table.table tbody tr') }
   }

   FindOwnersResultPage assertOwnerPresent(String firstName, String lastName, String address, String city,
                                           String telephone) {
      for (OwnersTableRowModule row : rowsInTable) {
         if (row.name == "$firstName $lastName" && row.address == address
            && row.city == city && row.telephone == telephone) {
            return this
         }
      }
      fail("No owner $firstName $lastName ($address, $city, $telephone) present.")
      return null
   }

   ShowOwnerPage openDetailsForOwner(String firstName, String lastName) {
      for (OwnersTableRowModule row : rowsInTable) {
         if (row.name == "$firstName $lastName") {
            return row.openDetails()
         }
      }
      fail("No owner $firstName $lastName present.")
      return null
   }
}
