package com.gaofei.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefrencesUtil {
	
	private static SharedPreferences sp;
	private static SharedPreferences sp2;
	public static void saveBoolean (Context context,String key,boolean value) {
		if(sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
				
	}
	public static boolean getBoolean (Context context ,String key,boolean defaultValue){
		if(sp==null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, defaultValue);
		
	}
	
	public static void saveString (Context context,String key,String value) {
		if(sp2==null) {
			sp2 = context.getSharedPreferences("news", Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
				
	}
	public static String getString (Context context ,String key,String defaultValue){
		if(sp2==null) {
			sp2 = context.getSharedPreferences("news", Context.MODE_PRIVATE);
		}
		return sp.getString(key, defaultValue);
		
	}
}
