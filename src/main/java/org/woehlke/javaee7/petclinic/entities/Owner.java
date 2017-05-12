package org.woehlke.javaee7.petclinic.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "owners")
@NamedQueries(
   {@NamedQuery(name = Owner.NQ_FIND_OWNERS_WITH_VISIT_WITHIN_GIVEN_TIME_FRAME,
      query = "SELECT o from Owner o"
         + " join o.pets p"
         + " join p.visits v"
         + " where v.date >= :" + Owner.NQP_START_DATE
         + " and v.date <= :" + Owner.NQP_END_DATE
   ),
      @NamedQuery(name = Owner.NQ_OWNERS_BY_PATTERNS,
         query = "select o from Owner o "
            + "left outer join o.pets p where lower(o.firstName) like :" + Owner.NQP_PATTERN
            + " or lower(o.lastName) like :"
            + Owner.NQP_PATTERN + " or lower(o.city) like :" + Owner.NQP_PATTERN + " or lower(p.name) like :"
            + Owner.NQP_PATTERN)}
)
public class Owner {

   public static final String NQ_FIND_OWNERS_WITH_VISIT_WITHIN_GIVEN_TIME_FRAME =
      "NQ_FIND_OWNERS_WITH_VISIT_WITHIN_GIVEN_TIME_FRAME";
   public static final String NQ_OWNERS_BY_PATTERNS = "Owner.byPattern";
   public static final String NQP_PATTERN = "PATTERN";
   public static final String NQP_START_DATE = "START_DATE";
   public static final String NQP_END_DATE = "END_DATE";
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "first_name")
   @NotEmpty
   @Length(min = 2, max = 30)
   private String firstName;

   @Column(name = "last_name")
   @NotEmpty
   @Length(min = 2, max = 30)
   private String lastName;

   @Column(name = "address")
   @NotEmpty
   @Length(min = 2, max = 50)
   private String address;

   @Column(name = "city")
   @NotEmpty
   @Length(min = 2, max = 30)
   private String city;

   @Column(name = "telephone")
   @NotEmpty
   @Digits(fraction = 0, integer = 11)
   @Length(min = 3, max = 10, message = "muss zwischen 3 und 10 Ziffern lang sein")
   private String telephone;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
   private Set<Pet> pets = new TreeSet<>();

   public void addPet(Pet pet) {
      pets.add(pet);
      pet.setOwner(this);
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

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getTelephone() {
      return telephone;
   }

   public void setTelephone(String telephone) {
      this.telephone = telephone;
   }

   public List<Pet> getPets() {
      List<Pet> list = new ArrayList<>();
      for (Pet pet : pets) {
         list.add(pet);
      }
      Collections.sort(list);
      return list;
   }

   public void setPets(Set<Pet> pets) {
      this.pets = pets;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (address != null ? address.hashCode() : 0);
      result = 31 * result + (city != null ? city.hashCode() : 0);
      result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
      result = 31 * result + (pets != null ? pets.hashCode() : 0);
      return result;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Owner)) return false;
      Owner owner = (Owner) o;
      if (address != null ? !address.equals(owner.address) : owner.address != null) return false;
      if (city != null ? !city.equals(owner.city) : owner.city != null) return false;
      if (firstName != null ? !firstName.equals(owner.firstName) : owner.firstName != null) return false;
      if (id != null ? !id.equals(owner.id) : owner.id != null) return false;
      if (lastName != null ? !lastName.equals(owner.lastName) : owner.lastName != null) return false;
      if (pets != null ? !pets.equals(owner.pets) : owner.pets != null) return false;
      if (telephone != null ? !telephone.equals(owner.telephone) : owner.telephone != null) return false;
      return true;
   }

   @Override
   public String toString() {
      return "Owner{" +
         "id=" + id +
         ", firstName='" + firstName + '\'' +
         ", lastName='" + lastName + '\'' +
         ", address='" + address + '\'' +
         ", city='" + city + '\'' +
         ", telephone='" + telephone + '\'' +
         ", pets=" + pets +
         '}';
   }
}
