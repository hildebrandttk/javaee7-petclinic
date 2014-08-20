package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Page

abstract class AbstractPetClinicPage extends Page {

   static content = {
      specialtiesLink { $("a", text: "Specialties") }
      veterinariansPageLink { $("a", text: "Veterinarians") }
      petTypePageLink { $("a", text: "Pet Types") }
      findOwnersLink { $("a", text: "Find Owners") }
   }

   SpecialtiesPage toSpecialties() {
      specialtiesLink.click()
      waitFor { browser.isAt(SpecialtiesPage) }
      return browser.page as SpecialtiesPage;
   }

   VeterinariansPage toVeterinarians() {
      veterinariansPageLink.click()
      waitFor { browser.isAt(VeterinariansPage) }
      return browser.page as VeterinariansPage;
   }

   PetTypesPage toPetTypes() {
      petTypePageLink.click()
      waitFor { browser.isAt(PetTypesPage) }
      return browser.page as PetTypesPage;
   }

   FindOwnersPage toFindOwners() {
      findOwnersLink.click()
      waitFor { browser.isAt(FindOwnersPage) }
      return browser.page as FindOwnersPage;
   }
}
