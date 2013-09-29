package com.charmyin.cmstudio.common.utils;

/**
 * Used for String operations
 * 
 * @author YinCM
 * @since 2013-9-29 21:34:14
 */
public class StringUtil {

	/**
	 * Is positive integer
	 * @param str
	 * @return 
	 */
	public static boolean isPositiveInteger(String str) {
		if(str==null || str.trim().equals("")){
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}
}
