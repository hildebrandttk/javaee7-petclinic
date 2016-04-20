package org.woehlke.javaee7.petclinic.services;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.woehlke.javaee7.petclinic.dao.VetDao;
import org.woehlke.javaee7.petclinic.entities.Specialty;
import org.woehlke.javaee7.petclinic.entities.Vet;
import org.woehlke.javaee7.petclinic.model.Vets;
import org.woehlke.javaee7.petclinic.util.RegexMatcher;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.expect;

@RunWith(Arquillian.class)
public class VetWebserviceTest {
   public static final SimpleDateFormat DATE_EXPRESSION = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'.*'");
   private static final String BASE = "../../../../";

   @ArquillianResource
   private URL baseUrl;

   @Deployment(testable = false)
   public static WebArchive createDeployment() {
      WebArchive war;
      war = ShrinkWrap.create(WebArchive.class, "remote-service-deployment.war")
         .addClasses(VetWebservice.class, JaxRsActivator.class, Vets.class)
         .addClasses(VetDao.class)
         .addClasses(Vet.class, Specialty.class)
         .addClasses(VetDaoMock.class);
      return war;
   }

   @Before
   public void setBaseURL() {
      RestAssured.baseURI = baseUrl.toString();
   }

   @Test
   @RunAsClient
   public void testFeed() throws IOException {
      final String datePattern = DATE_EXPRESSION.format(new Date());
      expect()
         .statusCode(200)
         .content(RegexMatcher.matchesRegex(
            "<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>"
               + "<atom:feed xmlns:atom=\"http://www.w3.org/2005/Atom\">"
               + "<atom:title>Veterinarians</atom:title>"
               + "<atom:updated>" + datePattern + "</atom:updated>"
               + "<atom:id>http://example.com/42</atom:id>"
               + "<atom:link href=\"http://localhost\" rel=\"edit\"/>"
               + "<atom:author><atom:name>Thomas Woehlke</atom:name></atom:author>"
               + "<atom:entry>"
               + "<atom:title>Vet: Hans Vogel</atom:title>"
               + "<atom:content type=\"html\">\\[Federn, Flügel, Körner\\]</atom:content>"
               + "</atom:entry>"
               + "<atom:entry>"
               + "<atom:title>Vet: Dieter Hund</atom:title>"
               + "<atom:content type=\"html\">\\[Fleisch, Katzen\\]</atom:content>"
               + "</atom:entry>"
               + "</atom:feed>"))
         .when()
         .get("rest/vets/feed");
   }
}
