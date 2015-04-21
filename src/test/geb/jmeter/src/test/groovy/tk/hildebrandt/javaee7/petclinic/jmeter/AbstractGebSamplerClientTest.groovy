package tk.hildebrandt.javaee7.petclinic.jmeter

import org.junit.Test

class AbstractGebSamplerClientTest {

    @Test
    public void testCreatedKeyIsNotNull(){
        assert AbstractGebSamplerClient.generateKey() != null
    }

    @Test
    public void testCreatedKeyLengthIsTen(){
        assert AbstractGebSamplerClient.generateKey().length() == 16
    }
}
