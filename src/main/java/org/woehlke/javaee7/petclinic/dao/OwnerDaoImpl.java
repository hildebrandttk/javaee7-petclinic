package org.woehlke.javaee7.petclinic.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.woehlke.javaee7.petclinic.entities.Owner;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.01.14
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class OwnerDaoImpl implements OwnerDao {

   private static Logger log = Logger.getLogger(OwnerDaoImpl.class.getName());

   @PersistenceContext(unitName = "javaee7petclinic")
   private EntityManager entityManager;

   @Override
   public List<Owner> getAll() {
      TypedQuery<Owner> q =
         entityManager.createQuery("select o from Owner o order by o.lastName,o.firstName", Owner.class);
      List<Owner> list = q.getResultList();
      return list;
   }

   @Override
   public void delete(long id) {
      Owner owner = entityManager.find(Owner.class, id);
      entityManager.remove(owner);
   }

   @Override
   public void addNew(Owner owner) {
      log.info("addNewOwner: " + owner.toString());
      entityManager.persist(owner);
   }

   @Override
   public Owner findById(long id) {
      return entityManager.find(Owner.class, id);
   }

   @Override
   public List<Owner> findOwnersWithVisitWithinGivenTimeFrame(final Date startDate, final Date endDate) {
      return entityManager
         .createNamedQuery(Owner.NQ_FIND_OWNERS_WITH_VISIT_WITHIN_GIVEN_TIME_FRAME)
         .setParameter(Owner.NQP_START_DATE, startDate)
         .setParameter(Owner.NQP_END_DATE, endDate)
         .getResultList();
   }

   @Override
   public void update(Owner owner) {
      log.info("updateOwner: " + owner.toString());
      owner = entityManager.merge(owner);
   }

   @Override
   public List<Owner> search(String searchTerm) {
      return entityManager.createNamedQuery(Owner.NQ_OWNERS_BY_PATTERNS, Owner.class)
         .setParameter(Owner.NQP_PATTERN, searchTerm.toLowerCase()).getResultList();
   }
}
