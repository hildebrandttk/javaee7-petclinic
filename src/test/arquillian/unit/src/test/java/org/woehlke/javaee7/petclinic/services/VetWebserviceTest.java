package org.woehlke.javaee7.petclinic.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.woehlke.javaee7.petclinic.dao.VetDao;
import org.woehlke.javaee7.petclinic.entities.Specialty;
import org.woehlke.javaee7.petclinic.entities.Vet;
import org.woehlke.javaee7.petclinic.model.Vets;
import org.woehlke.javaee7.petclinic.util.RegexMatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class VetWebserviceTest {
    private static final String BASE = "../../../../";
    private static final String POM_PATH = BASE + "pom.xml";

    @Deployment(testable = false)
    public static WebArchive createDaoDeployment() {
        File[] deps = Maven.resolver().loadPomFromFile(POM_PATH).importRuntimeDependencies().resolve().
            withTransitivity().asFile();
        WebArchive war;
        try {
            war = ShrinkWrap.create(WebArchive.class, "remote-service-deployment.war")
                .addClasses(VetWebservice.class, JaxRsActivator.class, Vets.class)
                .addClasses(VetDao.class)
                .addClasses(Vet.class, Specialty.class)
                .addClasses(VetDaoMock.class)
                .addAsLibraries(deps);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return war;
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    @RunAsClient
    public void testFeed() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        final String uri = baseURL + "rest/vets/feed";
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        assertEquals(200, response.getStatusLine().getStatusCode());
        final String datePattern = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'.*'").format(new Date());
        final HttpEntity entity = response.getEntity();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        entity.writeTo(outputStream);
        assertThat(outputStream.toString(), RegexMatcher.matchesRegex(
            "<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>"
                + "<atom:feed xmlns:atom=\"http://www.w3.org/2005/Atom\">"
                + "<atom:title>Veterinarians</atom:title>"
                + "<atom:updated>"+datePattern+"</atom:updated>"
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
                + "</atom:feed>"));
    }
}
