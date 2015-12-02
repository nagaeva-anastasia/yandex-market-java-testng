package config;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestConfigOld {

	private ArrayList<Element> nodes;

	public TestConfigOld(String path)
	{		
		nodes = GetTestConfig(path);
	}
	
	public ArrayList<Element> GetTestConfig(String path)
	{			
		try {
	        File fXmlFile = new File(path);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(fXmlFile); 
	        doc.getDocumentElement().normalize(); 
	        
	        NodeList nList = doc.getElementsByTagName("test");
	        nodes = new ArrayList<Element>();
	
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	Node nNode = nList.item(temp);		        
	        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	        		Element el = (Element)nNode;
			        nodes.add(el);
	        	}
	        }
       } catch (Exception e) {
    	   e.printStackTrace();
       }	
		
		return nodes;
	}	
	
	/*
	ConfigStruct[][] values = new ConfigStruct[nodes.size()][];
			
	for(Element node : nodes)
	{
		ConfigStruct config = new ConfigStruct();
		config.priceFrom = node.getElementsByTagName("priceFrom").item(0).getTextContent();
		config.priceTo = node.getElementsByTagName("priceTo").item(0).getTextContent();
		config.type = node.getElementsByTagName("type").item(0).getTextContent();
		config.platform = node.getElementsByTagName("platform").item(0).getTextContent();
		
		values.add(new configArray[] { config });
	}
	
	return values;
	*/
	
}
