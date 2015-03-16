package org.woehlke.javaee7.petclinic.web;

import java.io.File;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.woehlke.javaee7.petclinic.dao.OwnerDao;
import org.woehlke.javaee7.petclinic.dao.OwnerDaoImpl;
import org.woehlke.javaee7.petclinic.dao.PetDao;
import org.woehlke.javaee7.petclinic.dao.PetDaoImpl;
import org.woehlke.javaee7.petclinic.dao.PetTypeDao;
import org.woehlke.javaee7.petclinic.dao.PetTypeDaoImpl;
import org.woehlke.javaee7.petclinic.dao.SpecialtyDao;
import org.woehlke.javaee7.petclinic.dao.SpecialtyDaoImpl;
import org.woehlke.javaee7.petclinic.dao.VetDao;
import org.woehlke.javaee7.petclinic.dao.VetDaoImpl;
import org.woehlke.javaee7.petclinic.dao.VisitDao;
import org.woehlke.javaee7.petclinic.dao.VisitDaoImpl;
import org.woehlke.javaee7.petclinic.entities.Owner;
import org.woehlke.javaee7.petclinic.entities.Pet;
import org.woehlke.javaee7.petclinic.entities.PetType;
import org.woehlke.javaee7.petclinic.entities.Specialty;
import org.woehlke.javaee7.petclinic.entities.Vet;
import org.woehlke.javaee7.petclinic.entities.Visit;
import org.woehlke.javaee7.petclinic.services.OwnerService;
import org.woehlke.javaee7.petclinic.services.OwnerServiceImpl;

/**
 * Created with IntelliJ IDEA. User: tw Date: 20.01.14 Time: 08:45 To change this template use File | Settings | File
 * Templates.
 */
@ArquillianSuiteDeployment
public class Deployments {

   @Deployment(testable = false)
   public static WebArchive createFullDeployment() {
      return PetStoreDeployment.createFullDeployment();
   }
}
