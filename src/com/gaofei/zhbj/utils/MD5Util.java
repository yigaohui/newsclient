package com.gaofei.zhbj.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * md5加密
	 * @return
	 */
	public static String Md5(String message){
		StringBuilder sb = new StringBuilder();
		try {
			//algorithm: 加密的方�?
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			//1.将数据转化成byte数组，并对byte数组进行第一次加�?,返回的就是加密过的byte数组
			byte[] digest = messageDigest.digest(message.getBytes());
			for (int i = 0; i < digest.length; i++) {
				//2.将加密过的byte数组的元素和255进行与运�?
				//-128 - 127
				int result = digest[i] & 0xff;
				//因为得到int类型的�?�，可能会比较大，将int类型的�?�转化成十六进制的字符串
				String hexString = Integer.toHexString(result);
				if (hexString.length()<2) {
					//System.out.print("0");
					sb.append("0");
				}
				//System.out.println(hexString);
				sb.append(hexString);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			//找不到算法的异常
			e.printStackTrace();
		}
		return null;
	}
	
}
