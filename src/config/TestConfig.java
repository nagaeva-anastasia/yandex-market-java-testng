package config;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class TestConfig {		
	
	private File configFile;
	
	public TestConfig(String path)
	{			
		configFile = new File(path);
	}	
		
	public Tests[][] GetValues()
	{		 
		Tests[][] tests = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TestData.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TestData testData = (TestData) jaxbUnmarshaller.unmarshal(configFile);					
			
			tests = new Tests[testData.getTestsSize()][1];			
			for (int i = 0; i < testData.getTestsSize(); i++)
			{				
				Tests tt = testData.getTests()[i];
				tests[i][0] = tt;
			}
					
		} catch (JAXBException e) { 
			e.printStackTrace();
		}	
		
		return tests;
	}
}		