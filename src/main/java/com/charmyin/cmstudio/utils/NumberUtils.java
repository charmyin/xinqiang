package com.charmyin.cmstudio.utils;

import java.util.Random;

import org.junit.Test;

/**
 * 用于处理数字相关函数式
 * @author yincm
 *
 */
public class NumberUtils {
	
	/**
	 * 获取小于maxInt的随机数一个
	 * @param maxInt
	 * @return
	 */
	public static  int generateRandomInt(int maxInt){
		Random rand = new Random();
		return rand.nextInt(maxInt);
	}
}
