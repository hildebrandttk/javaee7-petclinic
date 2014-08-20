package tk.hildebrandt.javaee7.petclinic.cucumber

import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@Cucumber.Options(format = [ "pretty", "html:reports" ])
public class RunTest {
}
