package org.woehlke.javaee7.petclinic.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.woehlke.javaee7.petclinic.entities.Vet;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.01.14
 * Time: 08:30
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class VetDaoImpl implements VetDao {

   private static Logger log = Logger.getLogger(VetDaoImpl.class.getName());

   @PersistenceContext(unitName = "javaee7petclinic")
   private EntityManager entityManager;

   @Override
   public List<Vet> getAll() {
      TypedQuery<Vet> q = entityManager.createQuery("select v from Vet v order by v.lastName,v.firstName", Vet.class);
      List<Vet> list = q.getResultList();
      return list;
   }

   @Override
   public void delete(long id) {
      Vet vet = entityManager.find(Vet.class, id);
      entityManager.remove(vet);
   }

   @Override
   public void addNew(Vet vet) {
      log.info("addNewVet: " + vet.toString());
      entityManager.persist(vet);
   }

   @Override
   public Vet findById(long id) {
      Vet vet = entityManager.find(Vet.class, id);
      return vet;
   }

   @Override
   public void update(Vet vet) {
      entityManager.merge(vet);
   }

   @Override
   public List<Vet> search(String searchTerm) {
      TypedQuery<Vet> vetTypedQuery = entityManager.createNamedQuery(Vet.NQ_VET_BY_LAST_NAME_AND_FIRST_NAME, Vet.class)
         .setParameter(Vet.NQP_PATTERN, searchTerm.toLowerCase());
      return vetTypedQuery.getResultList();
   }
}
