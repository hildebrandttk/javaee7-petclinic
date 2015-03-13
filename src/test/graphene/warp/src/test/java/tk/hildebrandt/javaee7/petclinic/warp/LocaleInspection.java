package tk.hildebrandt.javaee7.petclinic.warp;

import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpServletRequest;

import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.jsf.AfterPhase;
import org.jboss.arquillian.warp.jsf.Phase;
import org.woehlke.javaee7.petclinic.web.LanguageBean;
import static org.junit.Assert.assertEquals;

public class LocaleInspection extends Inspection {
   @ArquillianResource
   private HttpServletRequest request;

   @ManagedProperty(value = "#{language}")
   private LanguageBean languageBean;

   @AfterPhase(Phase.RENDER_RESPONSE)
   public void verifyLocaleBean() {
      assertEquals("en", languageBean.getLocaleCode());
   }
}
