package com.KoreaIT.smw.demo.util;

public class Ut {

	
	public static boolean empty(Object obj) {
		if(obj == null) {
			return true;
		}
		// instanceof: 너 String 타입이니?
		if(obj instanceof String == false) {
			return true;
			
		}
		String str = (String) obj;

		return str.trim().length() == 0;
	}
}
