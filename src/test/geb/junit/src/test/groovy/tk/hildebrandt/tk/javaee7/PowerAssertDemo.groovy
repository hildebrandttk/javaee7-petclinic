package tk.hildebrandt.tk.javaee7

import org.junit.Test

class PowerAssertDemo {
    @Test
    public void showPowerAssertion(){
        assert PowerAssertDemo.class == getClass() && "thisIsATestString" == giveSomeString()
    }

    private String giveSomeString(){
        Random.newInstance().nextBoolean() ? 'foo' : 'bar'
    }
}