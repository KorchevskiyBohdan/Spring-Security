package com.spring.security.backend.security.filter.util;

public class FilterLogFormatUtil {
	private static final Integer MAX_CHARACTER_COUNT_TITLE = 64;
	private static final Integer MAX_CHARACTER_COUNT_EXCEPTION_PART = 40;
	
	public static String getFormatedTitle(String simpleName) {

		String initedName =  simpleName.trim();
		Integer l = initedName.length();
		while(l < MAX_CHARACTER_COUNT_TITLE) {
			initedName = "-" + initedName + "-";
			l = l+2;
		}
		
		return initedName;
	}
	
	public static String getFormatedExceptionLogPart(String simpleName) {
		
		String initedName =  simpleName.trim();
		Integer l = initedName.length();
		while(l < MAX_CHARACTER_COUNT_EXCEPTION_PART) {
			initedName = "*" + initedName + "*";
			l = l+2;
		}
		
		return "%s -->  EXCEPTION:".formatted(initedName);
	}
}
