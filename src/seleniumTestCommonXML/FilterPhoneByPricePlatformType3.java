package seleniumTestCommonXML;
import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import base.BaseSeleniumTest;
import yandexMarket.*;

public class FilterPhoneByPricePlatformType3 extends BaseSeleniumTest {	   
	
	@Parameters( {"browser"} )	
	@BeforeClass
	public void setUpBrowser(@Optional("chrome") String browser) {
		
			init("http://market.yandex.ru", browser);	        		     
    }
    
	@Parameters( {"priceFrom", "priceTo", "type", "platform"} )	
	@Test
	public void checkAll(String priceFrom,
			String priceTo,
			String type,
			String platform)
	{					
		MainPage mainPage = PageFactory.initElements(driver, MainPage.class);	                
        assertEquals("Logo is hidden and less than 80 x 30", true, mainPage.checkLogo(80, 30));
        
        MobilePhonePage mobilePhones = mainPage.NavigateToMobilePhones();        
        assertEquals("Header is not visible", true, mobilePhones.checkHeader());
        
        SearchResultPage searchPage = mobilePhones.FillFiltersAndSubmit(
        		priceFrom, 
        		priceTo, 	        		
        		platform, 
        		type);
        
        assertNotEquals("Search result page is empty", 0, searchPage.getProductCards().size());        
        assertEquals("Price from is wrong", priceFrom, searchPage.GetPriceFrom());        
        assertEquals("Price to is wrong", priceTo, searchPage.GetPriceTo());
        
        assertEquals("Type checkbox checked incorrectly", true, searchPage.IsCBChecked(type));
        assertEquals("Platform checkbox checked incorrectly", true, searchPage.IsCBChecked(platform));
        assertEquals("Count of checked checkboxes is wrong", 2, searchPage.GetCheckCBCount());
	}	
	
	@AfterClass
	public void Close()
	{
		close();
	}	
} 
