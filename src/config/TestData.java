package config;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class TestData {

    Tests[] tests;

    @XmlElement(name="tests")
    public Tests[] getTests() {
        return tests;
    }

    public void setTests(Tests[] tests) {
        this.tests = tests;
    }
    
    public int getTestsSize()
    {
    	return getTests().length;
    }
}
