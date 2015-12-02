package seleniumTestSeparatedXML;
import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import base.BaseSeleniumTest;
import yandexMarket.*;
import config.*;

public class FilterPhoneByPricePlatformType5 extends BaseSeleniumTest {	    
	
	private String path;
	
	@Parameters( { "pathToXML" })	
	public FilterPhoneByPricePlatformType5(String pathToXML)
	{
		path = pathToXML;
	}
	
	@DataProvider(name = "dataFromConfig")
	public Object[][] dataFromConfig() {
		return new TestConfig(path).GetValues();		
	}

	@Parameters( { "browser", "url" })	
	@BeforeClass
	public void setUpBrowser(@Optional("chrome") String browser, String url) {						
		init(url, browser);				
    }
    	
	@Test(dataProvider = "dataFromConfig")
	public void checkLogo(Tests test)
	{			
		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
		assertEquals("Logo is hidden and less than 80 x 30", true, mainPage.checkLogo(80, 30));        
        
        MobilePhonePage mobilePhones = mainPage.NavigateToMobilePhones();	                
        assertEquals("Header is not visible", true, mobilePhones.checkHeader());
        
        SearchResultPage searchPage = mobilePhones.FillFiltersAndSubmit(
        		test.getPriceFrom(), test.getPriceTo(), 
        		test.getPlatform(), test.getType());
        
        assertNotEquals("Search result page is empty", 0, searchPage.getProductCards().size());
        assertEquals("Price from is wrong", test.getPriceFrom(), searchPage.GetPriceFrom());	
        assertEquals("Price to is wrong", test.getPriceTo(), searchPage.GetPriceTo());
        assertEquals("Type checkbox checked incorrectly", true, searchPage.IsCBChecked(test.getType()));

		assertEquals("Platform checkbox checked incorrectly", true, searchPage.IsCBChecked(test.getPlatform()));
		assertEquals("Count of checked checkboxes is wrong", 2, searchPage.GetCheckCBCount());        
	}	
	
	@AfterClass
	public void Close()
	{
		close();
	}	
} 
