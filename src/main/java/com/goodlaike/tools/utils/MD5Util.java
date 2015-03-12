package com.goodlaike.tools.utils;

import java.security.MessageDigest;

/**
 * MD5工具类
 * 
 * @author Jail Hu
 */
public class MD5Util {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param str
	 *            字符串
	 * @return
	 * @throws Exception
	 * @since 1.0.0
	 * @author Jail Hu
	 * @createTime 2015年3月12日下午4:51:24
	 * @updator Jail Hu
	 * @updateTime 2015年3月12日下午4:51:24
	 */
	public static String encode(String str) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(str.getBytes());
		return getFormattedText(digest.digest());
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
}
