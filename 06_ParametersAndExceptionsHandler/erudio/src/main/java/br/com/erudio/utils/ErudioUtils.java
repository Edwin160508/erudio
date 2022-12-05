package br.com.erudio.utils;

import java.util.Objects;

public class ErudioUtils {

	public static Double convertToDouble(String strNumber) {
		if(Objects.isNull(strNumber)) return 0D;
		
		String number = strNumber.replaceAll(",", ".");
		
		if(isNumeric(number)) return Double.parseDouble(number);
		
		return 0D;		
	}
	
	public static boolean isNumeric(String strNumber) {
		if(Objects.isNull(strNumber)) return false;
		
		String number = strNumber.replaceAll(",", ".");
		
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
		
	}
}
