package tk.hildebrandt.javaee7.petclinic.geb.pages

import org.junit.Assert

class PetTypesPage extends AbstractPetClinicPage {

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'petTypes') }
      addNewPetType { $('a', text: 'Add New Pet Type') }
      rowsInTable { moduleList PetTypeTableRowModule, $('table.table tbody tr') }
   }

   NewPetTypePage openNewPetTypePage() {
      addNewPetType.click()
      waitForAtPage(NewPetTypePage)
   }

   PetTypesPage assertPetTypePresent(final String petTypeName) {
      for (PetTypeTableRowModule row : rowsInTable) {
         if (row.name == petTypeName) {
            return this
         }
      }
      Assert.fail("No pet type $petTypeName present.")
      return null
   }

   PetTypesPage assertPetTypeNotPresent(final String petTypeName) {
      int i = 1;
      for (PetTypeTableRowModule row : rowsInTable) {
         if (row.name == petTypeName) {
            Assert.fail("Found pet type $petTypeName in row $i")
         }
         i++;
      }
      return this;
   }

   EditPetTypePage openEditPetTypePage(String petTypeName) {
      for (PetTypeTableRowModule row : rowsInTable) {
         if (row.name == petTypeName) {
            return row.edit()
         }
      }
      Assert.fail("no row to edit $petTypeName")
      return null
   }

   PetTypesPage deletePetType(final String petTypeName) {
      for (PetTypeTableRowModule row : rowsInTable) {
         if (row.name == petTypeName) {
            return row.delete()
         }
      }
      Assert.fail("no row to delete $petTypeName")
      return null
   }
}
