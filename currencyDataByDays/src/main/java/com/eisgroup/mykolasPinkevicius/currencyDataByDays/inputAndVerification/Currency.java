package com.eisgroup.mykolasPinkevicius.currencyDataByDays.inputAndVerification;

import java.util.Scanner;

public class Currency {
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Currency(String code) {
		super();
		this.code = code;
	}
	
	public Currency() {};
	
	public static Currency collectCurrency() {
		
//		Creating scanner for System in data input
		@SuppressWarnings("resource")
		Scanner currScan = new Scanner(System.in);
		
//		Object for currency
		Currency curr = new Currency();
		
//		Currency
		System.out.println("Please input Currency Code");
		String currencyCode = currScan.nextLine();

		if (currencyCode.isEmpty()) {
			System.out.println("Currency code is empty, please input Currency Code");
			currencyCode = currScan.nextLine();
		} else if (currencyCode.length() < 3 || currencyCode.length() > 3) {
			System.out.println("Please check your currency code, it must be 3 letters");
			currencyCode = currScan.nextLine();
		}
		
//		Collecting Currency Code;
		curr.setCode(currencyCode);
		
		
////		Closing scanner
//		Can't close scanner because it closes system.in and then we cannot get input for dates
//		currScan.close();
	
		return curr;
		
	}
}
