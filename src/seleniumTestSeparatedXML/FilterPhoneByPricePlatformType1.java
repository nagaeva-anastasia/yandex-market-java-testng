package seleniumTestSeparatedXML;
import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import base.BaseSeleniumTest;
import yandexMarket.*;
import config.*;

public class FilterPhoneByPricePlatformType1 extends BaseSeleniumTest {	    

	private Object[][] isLogoOk;		
	@DataProvider(name = "isLogoOk")
	public Object[][] isLogoOk() {
		 return isLogoOk; 
	}
	
	private Object[][] isHeaderOk;	
	@DataProvider(name = "isHeaderOk")
	public Object[][] isHeaderOk() {
		 return isHeaderOk; 
	}
	
	private Object[][] cardSize;	
	@DataProvider(name = "cardSize")
	public Object[][] cardSize() {
		 return cardSize; 
	}
	
	private Object[][] priceFrom;	
	@DataProvider(name = "priceFrom")
	public Object[][] priceFrom() {
		 return priceFrom; 
	}
	
	private Object[][] priceTo;	
	@DataProvider(name = "priceTo")
	public Object[][] priceTo() {
		 return priceTo; 
	}
	
	private Object[][] isTypeChecked;	
	@DataProvider(name = "isTypeChecked")
	public Object[][] isTypeChecked() {
		 return isTypeChecked; 
	}
	
	private Object[][] isPlatformChecked;	
	@DataProvider(name = "isPlatformChecked")
	public Object[][] isPlatformChecked() {
		 return isPlatformChecked; 
	}
	
	private Object[][] checkedCount;	
	@DataProvider(name = "checkedCount")
	public Object[][] checkedCount() {
		 return checkedCount; 
	}
	
	public Object[] prepareTestData1D() {
		return new TestConfig("src/configData.xml").GetValues()[0];
	}
	
	@Parameters("browser")	
	@BeforeClass
	public void setUpBrowser(@Optional("chrome") String browser) {		
				
		Tests[] array = (Tests[]) prepareTestData1D();
		isLogoOk = new Object[array.length][8];
		isHeaderOk = new Object[array.length][8];
		cardSize = new Object[array.length][8];
		priceFrom = new Object[array.length][8];
		priceTo = new Object[array.length][8];
		isTypeChecked = new Object[array.length][8];
		isPlatformChecked = new Object[array.length][8];
		checkedCount = new Object[array.length][8];
		
		for (int i = 0; i < array.length; i++)
		{
			Tests valuesFromConfig = array[i];
			
			init("http://market.yandex.ru", browser);
	        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);	        
	        isLogoOk[i] = new Object[] { mainPage.checkLogo(80, 30) };
	        
	        MobilePhonePage mobilePhones = mainPage.NavigateToMobilePhones();	        
	        isHeaderOk[i] = new Object[] { mobilePhones.checkHeader() };
	        
	        SearchResultPage searchPage = mobilePhones.FillFiltersAndSubmit(
	        		valuesFromConfig.getPriceFrom(), 
	        		valuesFromConfig.getPriceTo(), 
	        		valuesFromConfig.getPlatform(), 
	        		valuesFromConfig.getType());
	        
	        cardSize[i] = new Object[] { searchPage.getProductCards().size() };
	        
	        priceFrom[i] = new Object[] { 
	        		searchPage.GetPriceFrom(), // price from actual
	        		valuesFromConfig.getPriceFrom() // price from expected;
	        };
	        
	        priceTo[i] = new Object[] { 
	        		searchPage.GetPriceTo(), // price to actual
	        		valuesFromConfig.getPriceTo() // price to expected};
	        };
	        
	        isTypeChecked[i] = new Object[] { searchPage.IsCBChecked(valuesFromConfig.getType()) };
	        isPlatformChecked[i] = new Object[] { searchPage.IsCBChecked(valuesFromConfig.getPlatform()) };
	        checkedCount[i] = new Object[] { searchPage.GetCheckCBCount() };

	        close();
		}		       
    }
    	
	@Test(dataProvider = "isLogoOk")
	public void checkLogo(boolean isLogoOk)
	{								
		assertEquals("Logo is hidden and less than 80 x 30", true, isLogoOk);		
	}	
	
	@Test(dataProvider = "isHeaderOk")
	public void checkMobilePhonesPageHeader(boolean isHeaderOk)
	{
		
		assertEquals("Header is not visible", true, isHeaderOk);				
	}
	
	@Test(dataProvider = "cardSize")
	public void checkSearchResultListIsNotEmpty(int cardSize)
	{					
		assertNotEquals("Search result page is empty", 0, cardSize);
	}
	
	@Test(dataProvider = "priceFrom")
	public void checkPriceFrom(String priceFromAct, String priceFromExp)
	{
		assertEquals("Price from is wrong", priceFromExp, priceFromAct);		
	}
	
	@Test(dataProvider = "priceTo")
	public void checkPriceTo(String priceToAct, String priceToExp)
	{		
		assertEquals("Price to is wrong", priceToExp, priceToAct);
	}
	
	@Test(dataProvider = "isTypeChecked")
	public void checkTypeCheckbox(boolean isTypeChecked)
	{
		assertEquals("Type checkbox checked incorrectly", true, isTypeChecked);
	}
	
	@Test(dataProvider = "isPlatformChecked")
	public void checkPlatformCheckbox(boolean isPlatformChecked)
	{
		assertEquals("Platform checkbox checked incorrectly", true, isPlatformChecked);
	}
	
	@Test(dataProvider = "checkedCount")
	public void checkCBCount(int checkedCount)
	{
		assertEquals("Count of checked checkboxes is wrong", 2, checkedCount);
	}	
} 
