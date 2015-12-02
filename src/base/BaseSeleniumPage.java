package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseSeleniumPage {
	protected WebDriver driver;
	
	public BaseSeleniumPage(WebDriver d)
	{
		driver = d;			
	}
	
	public boolean CheckImage(WebElement element, int minWidth, int maxWidth)
	{
    	long width;
    	long height;
    	
    	try
    	{
    		width = (long) ((JavascriptExecutor) driver).executeScript("return arguments[0].width", element);
        	height = (long) ((JavascriptExecutor) driver).executeScript("return arguments[0].height", element);	    		
    	}
    	catch (Exception ex)
    	{
    		return false;
    	}    	
    	
    	return element.isDisplayed() && 
    			width > minWidth && 
    			height > maxWidth;
	}
}
