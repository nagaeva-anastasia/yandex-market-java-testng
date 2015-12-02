package yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseSeleniumPage;

public class MainPage extends BaseSeleniumPage {
    @FindBy(xpath = "//li[@data-department='Электроника']/a")
    private WebElement electronicLink;
    
    @FindBy(xpath = "//a[contains(@class, 'topmenu__subite') and text() = 'Мобильные телефоны']")
    private WebElement mobilePhone;
    
    @FindBy(xpath = "//div[@class='header2__logo']/a/img")
    private WebElement logo;
    
    public MainPage(WebDriver driver) { 
    	super(driver);    	
    }
    
    public boolean checkLogo(int minWidth, int minHeight)
    {
    	return CheckImage(logo, minWidth, minHeight);
    }
    
    public MobilePhonePage NavigateToMobilePhones()
    {
    	electronicLink.click();		
		mobilePhone.click();
		
		return PageFactory.initElements(driver, MobilePhonePage.class);
    }
}
