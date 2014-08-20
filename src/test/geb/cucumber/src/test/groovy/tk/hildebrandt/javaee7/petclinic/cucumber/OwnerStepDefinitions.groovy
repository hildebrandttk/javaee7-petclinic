package tk.hildebrandt.javaee7.petclinic.cucumber

import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks
import geb.Browser
import geb.binding.BindingUpdater
import tk.hildebrandt.javaee7.petclinic.geb.pages.FindOwnersPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.FindOwnersResultPage
import tk.hildebrandt.javaee7.petclinic.geb.pages.HelloPage

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

Before() {
   bindingUpdater = new BindingUpdater(binding, new Browser())
   bindingUpdater.initialize()
}

After() {
   bindingUpdater.remove()
}

Given(~'^browser on find owner page$') { ->
   to(HelloPage)
      .toFindOwners()
}

Given(~'^browser on find owner result page$') { ->
   to(HelloPage)
      .toFindOwners()
      .searchForOwner('')
}

When(~'^create new owner (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*)$') { String firstName, String lastName, String street, String city, String phone ->
   ((FindOwnersPage)page).openNewOwnersPage()
      .addNewOwner(firstName, lastName, street, city, phone)
}

Then(~'^user (\\w*) (\\w*), living ([a-zA-Z0-9\\ \\.]*), (\\w*) with phone (\\d*) exists$') { String firstName, String lastName, String street, String city, String phone ->
   ((FindOwnersResultPage)page).assertOwnerPresent(firstName, lastName, street, city, phone)
}


