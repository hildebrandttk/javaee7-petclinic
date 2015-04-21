package tk.hildebrandt.javaee7.petclinic.geb.pages

import org.junit.Assert

class VeterinariansPage extends AbstractPetClinicPage {
   static url = 'vets.jsf'

   static at = { pageHeader.present }

   static content = {
      pageHeader { $('h2', id: 'veterinarians') }
      addNewSpecialty { $('a', text: 'Add New Veterinarian') }
      vetControllerSearchTerm { $('input.vetControllerSearchTerm') }
      vetControllerSearchBtn { $('input.vetControllerSearchBtn') }
      rowsInTable { moduleList VeterinariansTableRowModule, $('#veterinariansForm\\:veterinariansTable tbody tr') }
   }

   NewVeterinarianPage openNewVeterinarianPage() {
      addNewSpecialty.click()
      waitForAtPage(NewVeterinarianPage)
   }

   VeterinariansPage assertVeterinarianPresent(final String firstName, final String lastName, final String... specialties) {
      vetControllerSearchTerm.value(lastName)
      vetControllerSearchBtn.click()
      for (VeterinariansTableRowModule row : rowsInTable) {
         if (row.lastName == lastName && row.firstName == firstName) {
            if(specialties != null) {
               boolean matched = true
               String selectedSpecialties = row.specialties
               for (String specialty : specialties) {
                  if(!selectedSpecialties.contains(specialty)){
                     matched = false
                  }
               }
               if(matched){
                  return this
               }
            }
            return this
         }
      }
      Assert.fail("No veterinarian $firstName $lastName with specialties $specialties present.")
      return null
   }

   VeterinariansPage assertVeterinarianNotPresent(final String firstName, final String lastName) {
      int i = 1;
      vetControllerSearchTerm.value(lastName)
      vetControllerSearchBtn.click()
      for (VeterinariansTableRowModule row : rowsInTable) {
         if (row.lastName == lastName && row.firstName == firstName) {
            Assert.fail("Found veterinarian $firstName $lastName in row $i")
         }
         i++;
      }
      return this;
   }

   EditVeterinarianPage openEditVeterinarianPage(final String firstName, final String lastName) {
      vetControllerSearchTerm.value(lastName)
      vetControllerSearchBtn.click()
      for (VeterinariansTableRowModule row : rowsInTable) {
         if (row.lastName == lastName && row.firstName == firstName) {
            return row.edit()
         }
      }
      Assert.fail("no row to edit $firstName $lastName")
      return null
   }

   VeterinariansPage deleteVeterinarian(final String firstName, final String lastName) {
      vetControllerSearchTerm.value(lastName)
      vetControllerSearchBtn.click()
      for (VeterinariansTableRowModule row : rowsInTable) {
         if (row.lastName == lastName && row.firstName == firstName) {
            return row.delete()
         }
      }
      Assert.fail("no row to delete $firstName $lastName")
      return null
   }
}
