package com.gaofei.zhbj.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class CacheMapUtil {

	
	private  MemoryCache cache=new MemoryCache();
	private LocalCache cache2=new LocalCache();
	private NetCache cache3=new NetCache(cache2,cache);
	
	
	
	public  void display(String url,ImageView iamge){
				
		Bitmap bitmap;
		bitmap=cache.getBitMap(url);
		//System.out.println(bitmap);
		if(bitmap!=null) {
			iamge.setImageBitmap(bitmap);
			return;
		}
		
		bitmap=cache2.getBitMap(url);
		if(bitmap!=null) {
			iamge.setImageBitmap(bitmap);
			cache.setBitMap(url, bitmap);
			return;
		}
		cache3.getBitmap(iamge, url);
		
	}
	
}
