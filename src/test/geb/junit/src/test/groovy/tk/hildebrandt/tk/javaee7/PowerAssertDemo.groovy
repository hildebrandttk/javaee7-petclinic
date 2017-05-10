package tk.hildebrandt.tk.javaee7

import org.junit.Ignore
import org.junit.Test

class PowerAssertDemo {
    @Test
    @Ignore("for demo only, fails sometimes by design")
    public void showPowerAssertion(){
        assert PowerAssertDemo.class == getClass() && 'foo' == giveSomeString()
    }

    private String giveSomeString(){
        Random.newInstance().nextBoolean() ? 'foo' : 'bar'
    }
}
