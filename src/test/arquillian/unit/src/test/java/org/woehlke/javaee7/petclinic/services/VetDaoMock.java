package org.woehlke.javaee7.petclinic.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import org.woehlke.javaee7.petclinic.dao.VetDao;
import org.woehlke.javaee7.petclinic.entities.Specialty;
import org.woehlke.javaee7.petclinic.entities.Vet;

@Stateless
public class VetDaoMock implements VetDao {
   @Override
   public List<Vet> getAll() {
       final Set<Specialty> specialtiesVogel = new HashSet<>();
       specialtiesVogel.add(new Specialty("Flügel"));
       specialtiesVogel.add(new Specialty("Körner"));
       specialtiesVogel.add(new Specialty("Federn"));
       final Set<Specialty> specialtiesHund = new HashSet<>();
       specialtiesHund.add(new Specialty("Fleisch"));
       specialtiesHund.add(new Specialty("Katzen"));
       return Arrays.asList(
         new Vet("Hans", "Vogel", specialtiesVogel),
         new Vet("Dieter", "Hund", specialtiesHund)
      );
   }

   @Override
   public void delete(final long id) {
   }

   @Override
   public void addNew(final Vet vet) {
   }

   @Override
   public Vet findById(final long id) {
      return null;
   }

   @Override
   public void update(final Vet vet) {
   }

   @Override
   public List<Vet> search(final String searchterm) {
      return null;
   }
}
