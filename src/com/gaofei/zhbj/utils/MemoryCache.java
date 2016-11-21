package com.gaofei.zhbj.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryCache {

	
	
	private LruCache<String, Bitmap> bitmapLru;

	public MemoryCache(){
		bitmapLru = new LruCache<String, Bitmap>((int) ((Runtime.getRuntime().maxMemory())/8)){
			
			@Override
			protected int sizeOf(String key, Bitmap value) {
				
				int byteCount = value.getRowBytes()*value.getHeight();
				return byteCount ;
				
				
			}
		};
	}
	
	public void setBitMap(String url,Bitmap bitmap) {
		
		bitmapLru.put(url, bitmap);
	}
	public Bitmap getBitMap(String url) {
		System.out.println("从内存中获取");
		return bitmapLru.get(url);
	}
	
	
}
