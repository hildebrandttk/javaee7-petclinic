package tk.hildebrandt.javaee7.petclinic.geb.pages

import geb.Page

abstract class AbstractPetClinicPage<T extends AbstractPetClinicPage> extends Page {

   static content = {
      specialtiesLink { $("a", text: "Specialties") }
      veterinariansPageLink { $("a", text: "Veterinarians") }
      petTypePageLink { $("a", text: "Pet Types") }
      findOwnersLink { $("a", text: "Find Owners") }
      languageSelection { $("select.languageSelection") }
   }

   SpecialtiesPage toSpecialties() {
      specialtiesLink.click()
      return waitForAtPage(SpecialtiesPage);
   }

   VeterinariansPage toVeterinarians() {
      veterinariansPageLink.click()
      return waitForAtPage(VeterinariansPage)
   }

   PetTypesPage toPetTypes() {
      petTypePageLink.click()
      return waitForAtPage(PetTypesPage);
   }

   FindOwnersPage toFindOwners() {
      findOwnersLink.click()
      return waitForAtPage(FindOwnersPage);
   }
   T selectLanguage(Locale locale) {
      languageSelection.value("Deutsch")
      return this;
   }

   def <T extends Page> T waitForAtPage(Class<T> targetPageClass){
      waitFor { browser.isAt(targetPageClass) }
      return browser.page as T;
   }
}
