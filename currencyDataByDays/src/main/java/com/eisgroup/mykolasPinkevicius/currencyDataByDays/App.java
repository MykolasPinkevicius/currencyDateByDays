package com.eisgroup.mykolasPinkevicius.currencyDataByDays;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.decimal4j.util.DoubleRounder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * @author mykolaspinkevicius
 *
 */
public class App {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{

//		Creating scanner for System in data input
		Scanner scan = new Scanner(System.in);
		
//		Taking Currency Code
		System.out.println("Please input Currency Code");
		String currencyCode = scan.nextLine();
		
//		Writing input rules
		System.out.println("Please input date from which you want to get data.\nUse this format 0000-00-00/year/month/day.\nEnter Starting date, than finishing date");
		System.out.println("\nEnter Starting Date (Enter weekend's only if you want larger data than few days)");
		
//		Star dat
		String startingDate = scan.nextLine();
//		FInishing date
		System.out.println("Enter Finishing Date");
		String finishingDate = scan.nextLine();
		System.out.println("\n");
		
//		Closing scanner for optimal performance
		scan.close();
		
//		We take data from HTTP Get method
		URL address = new URL("https://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=EU&ccy="
				+ currencyCode + "&dtFrom=" + startingDate + "&dtTo=" + finishingDate);
		
		URLConnection conn = address.openConnection();
		
		InputStream in = conn.getInputStream();
		
		BufferedInputStream buff = new BufferedInputStream(in);
//		Put data into string
		String str = new String(buff.readAllBytes());
		
//		Close readers
		buff.close();
		in.close();
	
//		XML string formatted data to file or we could straight give it to creating Node object but 
//		I created file for if something happen we could see last request who maybe coused the error
		FileWriter fw = new java.io.FileWriter("Resources/my-file.xml");
	    fw.write(str);
	    fw.close();
		   
	    
//	    Parsing XML file
	    File fXmlFile = new File("Resources/my-file.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		
		
//		Creating Node Object FxRate
		NodeList nList = doc.getElementsByTagName("FxRate");
		
		System.out.println("----------------------------");
		
//		Collection creating for adding double elements to list
		List<Double> doublesArray = new ArrayList<>(); 
		
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) nNode;

//				Was left for cheking if data was there.
//				System.out.println("Data " + eElement.getElementsByTagName("Dt").item(0).getTextContent());
//				System.out.println("Currency code and rate to Euro " + eElement.getElementsByTagName("CcyAmt").item(1).getTextContent());
				String elementas = eElement.getElementsByTagName("CcyAmt").item(1).getTextContent().toString();
				
				
//				Getting double out of string.
				Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
				Matcher m = p.matcher(elementas);
				
				while(m.find()) { 
				    Double dInf = Double.parseDouble(m.group(1));
				    doublesArray.add(dInf);
				}
			}	
			
		}
		
		System.out.println("\n");
		
//		Getting average change from List of Double's and displaying to user
		Double average = DoubleRounder.round(doublesArray.get(doublesArray.size() - 1), 4) - DoubleRounder.round(doublesArray.get(0), 4);
		
//		Using BigDEcimal for minus arithmetic values
		BigDecimal b  = BigDecimal.valueOf(average);
		
//		Roundind the BigDecimal average value;
		MathContext mc = new MathContext(4);
		b = b.round(mc);
		
		System.out.println("Currency rate changes by " + b + " points\nfrom " + startingDate + " to " + finishingDate);

		
	}

	
}


