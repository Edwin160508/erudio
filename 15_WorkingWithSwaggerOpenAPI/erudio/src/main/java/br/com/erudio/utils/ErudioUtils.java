package br.com.erudio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ErudioUtils {
		
	public static Calendar stringToCalendar(String dateString) throws ParseException {
		//2016-03-04T11:30:23
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		cal.setTime(sdf.parse(dateString));
		return cal;		
	}
	
	public static String calendarToString(Calendar dateCalendar) {
		//2016-03-04T11:30
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date date = dateCalendar.getTime();
		
		return sdf.format(date);
	}
}
