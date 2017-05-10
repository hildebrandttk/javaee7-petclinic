package org.woehlke.javaee7.petclinic.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "vets")
@NamedQuery(name = Vet.NQ_VET_BY_LAST_NAME_AND_FIRST_NAME,
   query = "select v from Vet v where lower(v.firstName) like :" + Vet.NQP_PATTERN + " or lower(v.lastName) like :" + Vet.NQP_PATTERN)
public class Vet {

   public static final String NQ_VET_BY_LAST_NAME_AND_FIRST_NAME = "Vet.byLastNameAndFirstName";
   public static final String NQP_PATTERN = "PATTERN";

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "first_name")
   @NotEmpty
   private String firstName;

   @Column(name = "last_name")
   @NotEmpty
   private String lastName;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "vet_specialties",
      joinColumns = @JoinColumn(name = "vet_id"),
      inverseJoinColumns = @JoinColumn(name = "specialty_id"))
   private java.util.Set<Specialty> specialties;

   public Vet() {
      //for framework usage
   }

   public Vet(final String firstName, final String lastName,
              final Set<Specialty> specialties) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.specialties = specialties;
   }

   protected Set<Specialty> getSpecialtiesInternal() {
      if (this.specialties == null) {
         this.specialties = new HashSet<Specialty>();
      }
      return this.specialties;
   }

   @XmlElement
   public List<Specialty> getSpecialties() {
      List<Specialty> list = new ArrayList<Specialty>();
      for (Specialty s : getSpecialtiesInternal()) {
         list.add(s);
      }
      Collections.sort(list);
      return list;
   }

   public void setSpecialties(Set<Specialty> specialties) {
      this.specialties = specialties;
   }

   public String getSpecialtiesAsString() {
      StringBuilder stringBuilder = new StringBuilder();
      if (getNrOfSpecialties() == 0) {
         stringBuilder.append("none");
      } else {
         for (Specialty specialty : getSpecialties()) {
            stringBuilder.append(specialty.getName());
            stringBuilder.append(" ");
         }
      }
      return stringBuilder.toString();
   }

   public int getNrOfSpecialties() {
      return getSpecialtiesInternal().size();
   }

   public void addSpecialty(Specialty specialty) {
      getSpecialtiesInternal().add(specialty);
   }

   public void removeSpecialties() {
      this.specialties = new HashSet<Specialty>();
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @Override
   public String toString() {
      return "Vet{" +
         "id=" + id +
         ", firstName='" + firstName + '\'' +
         ", lastName='" + lastName + '\'' +
         ", specialties=" + specialties +
         '}';
   }
}
