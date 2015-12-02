package config;
import javax.xml.bind.annotation.*;

@XmlType(propOrder={"priceFrom", "priceTo", "type", "platform"})
public class Tests {

	private String priceFrom;	
	private String priceTo;
	private String platform;
	private String type;
	
    public String getPriceFrom() {
        return priceFrom;
    }

    @XmlElement
    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    @XmlElement
    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }
    
    public String getPlatform() {
        return platform;
    }

    @XmlElement
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }
}
