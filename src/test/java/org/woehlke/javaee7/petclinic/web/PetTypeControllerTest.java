package org.woehlke.javaee7.petclinic.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.richfaces.component.SortOrder;
import static org.junit.Assert.assertEquals;

public class PetTypeControllerTest {

   @Test
   public void testSwitchSortOrder_ascending_to_descending() {
      PetTypeController petTypeController = new PetTypeController(SortOrder.ascending);

      petTypeController.switchSortOrder();

      assertEquals(SortOrder.descending, petTypeController.getPetTypeSortOrder());
   }

   @Test
   public void testSwitchSortOrder_descending_to_ascending() {
      PetTypeController petTypeController = new PetTypeController(SortOrder.descending);

      petTypeController.switchSortOrder();

      assertEquals(SortOrder.ascending, petTypeController.getPetTypeSortOrder());
   }
}
