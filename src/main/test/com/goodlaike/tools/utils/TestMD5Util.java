package com.goodlaike.tools.utils;

import org.junit.Test;

/**
 * 
 * @author Jail Hu
 */
public class TestMD5Util {

	@Test
	public void encode(){
		try {
			System.out.println(MD5Util.encode("123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
