package seleniumTestCommonXML;
import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import base.BaseSeleniumTest;
import yandexMarket.*;

public class FilterPhoneByPricePlatformType2 extends BaseSeleniumTest {	   
	
	boolean isLogoOk;
	boolean isHeaderOk;
	int cardSize;
	String priceFromAct;
	String priceFromExp;
	String priceToAct;
	String priceToExp;
	boolean isTypeChecked;
	boolean isPlatformChecked;
	int checkedCount;
	
	@Parameters( {"browser", "priceFrom", "priceTo", "type", "platform"} )	
	@BeforeClass
	public void setUpBrowser(@Optional("chrome") String browser,
			String priceFrom,
			String priceTo,
			String type,
			String platform) {
		
			init("http://market.yandex.ru", browser);
	        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);	        
	        isLogoOk = mainPage.checkLogo(80, 30);
	        
	        MobilePhonePage mobilePhones = mainPage.NavigateToMobilePhones();	        
	        isHeaderOk = mobilePhones.checkHeader();
	        
	        SearchResultPage searchPage = mobilePhones.FillFiltersAndSubmit(
	        		priceFrom, 
	        		priceTo, 	        		
	        		platform, 
	        		type);
	        
	        cardSize = searchPage.getProductCards().size();
	        
	        priceFromAct = searchPage.GetPriceFrom(); // price from actual
	        priceFromExp = priceFrom; // price from expected;	        
	        
	        priceToAct = searchPage.GetPriceTo(); // price from actual
	        priceToExp = priceTo; // price from expected;
	        
	        isTypeChecked = searchPage.IsCBChecked(type);
	        isPlatformChecked = searchPage.IsCBChecked(platform);
	        checkedCount = searchPage.GetCheckCBCount();		      
    }
    	
	@Test
	public void checkLogo()
	{								
		assertEquals("Logo is hidden and less than 80 x 30", true, isLogoOk);		
	}	
	
	@Test
	public void checkMobilePhonesPageHeader()
	{
		assertEquals("Header is not visible", true, isHeaderOk);				
	}
	
	@Test
	public void checkSearchResultListIsNotEmpty()
	{					
		assertNotEquals("Search result page is empty", 0, cardSize); 
	}
	
	@Test
	public void checkPriceFrom()
	{
		assertEquals("Price from is wrong", priceFromExp, priceFromAct);		
	}
	
	@Test
	public void checkPriceTo()
	{		
		assertEquals("Price to is wrong", priceToExp, priceToAct);
	}
	
	@Test
	public void checkTypeCheckbox()
	{
		assertEquals("Type checkbox checked incorrectly", true, isTypeChecked);
	}
	
	@Test
	public void checkPlatformCheckbox()
	{
		assertEquals("Platform checkbox checked incorrectly", true, isPlatformChecked);
	}
	
	@Test
	public void checkCBCount()
	{
		assertEquals("Count of checked checkboxes is wrong", 2, checkedCount);
	}	
	
	@AfterClass
	public void Close()
	{
		close();
	}	
} 
