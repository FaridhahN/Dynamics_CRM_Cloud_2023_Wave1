package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;

import pages.MemberFormPage;

public class TestUtils {

	public static String date;
	public static String enddate;
	public static String getDate() {
	LocalDate currentdate = LocalDate.now();
	return ""+currentdate.getMonth()+currentdate.getDayOfMonth();
	
	}
	
	public static String getRandomNumberString() {
	    // It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);
	}

	
	public static String generateRandomChars(String candidateChars, int length) {
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }

	    return sb.toString();
	}

	public static String todaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		String enddate= dateFormat.format(date);
		return enddate;
		}
	
	//Enter End Date as FutureDate Date in Account Numbers
		public static String  FutureEndDate(int number) {
			DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
			Date date = new Date();

			Calendar c = Calendar.getInstance();    
			c.setTime(date);        
			// manipulate date        
			c.add(Calendar.DATE, number); 
			// convert calendar to date      
			Date currentDatePlusOne = c.getTime();

			String enddate= dateFormat.format(currentDatePlusOne);			
			return enddate;
		}
	
}
