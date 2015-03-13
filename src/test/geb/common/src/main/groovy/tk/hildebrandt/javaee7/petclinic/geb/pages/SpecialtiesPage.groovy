package tk.hildebrandt.javaee7.petclinic.geb.pages

import org.junit.Assert

class SpecialtiesPage extends AbstractPetClinicPage {

   static url = 'specialties.jsf'
   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'specialties') }
      addNewSpecialty { $('a', text: 'Add New Specialty') }
      rowsInTable { moduleList SpecialtiesTableRowModule, $('table.table tbody tr') }
   }

   NewSpecialtiesPage openNewSpecialityPage() {
      addNewSpecialty.click()
      waitForAtPage(NewSpecialtiesPage)
   }

   SpecialtiesPage assertSpecialityPresent(final String specialityName) {
      for (SpecialtiesTableRowModule row : rowsInTable) {
         String name = row.name
         if (name == specialityName) {
            return this
         }
      }
      Assert.fail("No speciality $specialityName present.")
      return null
   }

   SpecialtiesPage assertSpecialityNotPresent(final String specialityName) {
      int i = 1;
      for (SpecialtiesTableRowModule row : rowsInTable) {
         if (row.name == specialityName) {
            Assert.fail("Found speciality $specialityName in row $i")
         }
         i++;
      }
      return this;
   }

   EditSpecialtiesPage openEditSpecialtyPage(String specialityName) {
      for (SpecialtiesTableRowModule row : rowsInTable) {
         if (row.name == specialityName) {
            return row.edit()
         }
      }
      Assert.fail("no row to edit $specialityName")
      return null
   }

   SpecialtiesPage deleteSpecialty(final String specialityName) {
      for (SpecialtiesTableRowModule row : rowsInTable) {
         if (row.name == specialityName) {
            return row.delete()
         }
      }
      Assert.fail("no row to delete $specialityName")
      return null
   }
}
