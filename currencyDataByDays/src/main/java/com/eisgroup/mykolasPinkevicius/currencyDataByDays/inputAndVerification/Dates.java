package com.eisgroup.mykolasPinkevicius.currencyDataByDays.inputAndVerification;

import java.util.Scanner;
/**
 * @author mykolaspinkevicius
 *
 */
public class Dates {
	
	private String startDate;
	private String finishDate;

	public Dates(String startDate, String finishDate) {
		super();
		this.startDate = startDate;
		this.finishDate = finishDate;
	}
	
	public Dates() {};


	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getFinishDate() {
		return finishDate;
	}



	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}



	public static Dates collectDates() {
		
//		Creating scanner for System in data input
		Scanner dateScan = new Scanner(System.in);
		
//		Object for dates
		Dates dates = new Dates();
		
//		Start date
		System.out.println("Enter start year");
		String startYear = dateScan.nextLine();
		if(startYear.isEmpty()) {
			System.out.println("Please input start year, it's 4 number format '0000'");
			startYear = dateScan.nextLine();
		}
		else if (startYear.length() > 4 || startYear.length() < 4) {
			System.out.println("Enter the year, it's 4 number format '0000'");
			startYear = dateScan.nextLine();
		}

		System.out.println("Enter start month");
		String startMonth = dateScan.nextLine();
		if(startMonth.isEmpty()) {
			System.out.println("Please input start Month, it's 2 number format '00'");
			startMonth = dateScan.nextLine();
		}
		else if (startMonth.length() > 2 || startMonth.length() < 2) {
			System.out.println("Enter the month correctly, it's 2 number format '00'");
			startMonth = dateScan.nextLine();
		}
		
		System.out.println("Enter start day");
		String startDay = dateScan.nextLine();
		if(startDay.isEmpty()) {
			System.out.println("Please inpute start Day, it's 2 number format '00'");
			startDay = dateScan.nextLine();
		} else if(startDay.length() > 2 || startDay.length() < 2) {
			System.out.println("Enter the day correctly, it's 2 number format '00'");
			startDay = dateScan.nextLine();
		}
			String startDate = startYear + "-" + startMonth + "-" + startDay;
			
			
//		Finish date
			System.out.println("Enter finish year");
			String finishYear = dateScan.nextLine();
			if(finishYear.isEmpty()) {
				System.out.println("Please input finish year, it's 4 number format '0000'");
				finishYear = dateScan.nextLine();
			}
			else if (finishYear.length() > 4 || finishYear.length() < 4) {
				System.out.println("Enter the year correctly, it's 4 number format '0000'");
				finishYear = dateScan.nextLine();
			}
			
			System.out.println("Enter finish month");
			String finishMonth = dateScan.nextLine();
			if(finishMonth.isEmpty()) {
				System.out.println("Please input finish Month, it's 2 number format '00'");
				finishMonth = dateScan.nextLine();
			}
			else if (finishMonth.length() > 2 || finishMonth.length() < 2) {
				System.out.println("Enter the month correctly, it's 2 number format '00'");
				finishMonth = dateScan.nextLine();
			}
			
			System.out.println("Enter finish day");
			String finishDay = dateScan.nextLine();
			if(finishDay.isEmpty()) {
				System.out.println("Please inpute finish Day, it's 2 number format '00'");
				finishDay = dateScan.nextLine();
			} else if(finishDay.length() > 2 || finishDay.length() < 2) {
				System.out.println("Enter the day correctly, it's 2 number format '00'");
				finishDay = dateScan.nextLine();
			}
				String finishDate = finishYear + "-" + finishMonth + "-" + finishDay;
			
		
//		Collecting dates
		dates.setStartDate(startDate);
		dates.setFinishDate(finishDate);
		
//		Close scanner
		dateScan.close();
		return dates;
	}
}
