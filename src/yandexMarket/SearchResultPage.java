package yandexMarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.TransformerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseSeleniumPage;
import elements.ProductCard;

public class SearchResultPage extends BaseSeleniumPage {
	@FindBy(xpath = "//input[@id='gf-pricefrom-var']")
	private WebElement priceFrom;
    
    @FindBy(xpath = "//input[@id='gf-priceto-var']")
    private WebElement priceTo;
    
    @FindBy(xpath = "//span[contains(@class, 'checkbox_checked_yes')]")
    private List<WebElement> checkedBoxes;
    
    @FindBy(xpath = "//div[contains(@data-id, 'model')]")
    private List<WebElement> productCards;
            
    public SearchResultPage(WebDriver driver) { 
    	super(driver);    	    	
    }
    
    public ArrayList<ProductCard> getProductCards()
    {
    	ArrayList<ProductCard> cards = new ArrayList<ProductCard>();
    	
    	for (WebElement card : productCards)
    	{
    		ProductCard cardPage = new ProductCard(driver, card);
        	PageFactory.initElements(driver, cardPage);
        	cards.add(cardPage);
    	}    	
    	
    	return cards;
    }
    
    public String GetPriceFrom()
    {
    	return priceFrom.getAttribute("value");    	
    }
    
    public String GetPriceTo()
    {
    	return priceTo.getAttribute("value");    	
    }
    
    public boolean IsCBChecked(String pattern)
    {    	    	    	 
    	Collection<String> checkedBoxesCollection = CollectionUtils.collect(checkedBoxes, TransformerUtils.invokerTransformer("getText"));		     	
    
    	return checkedBoxesCollection.contains(pattern);
    }
    
    public int GetCheckCBCount()
    {    	
    	return checkedBoxes.size();
    }
}
