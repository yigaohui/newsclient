package com.gaofei.zhbj.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class LocalCache {
	private String Path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/zhbj_cache";
	
	
	public void setBitMap(String url,Bitmap bitmap){
		File dir=new File(Path);
		if(!dir.exists()||!dir.isDirectory()){
			dir.mkdirs();
		}
		
		OutputStream stream;
		try {
			stream = new FileOutputStream(new File(dir,MD5Util.Md5(url).substring(0,10)));
			bitmap.compress(CompressFormat.JPEG, 100, stream );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Bitmap getBitMap(String url) {
		System.out.println("从本地中获取图片");
		File file =new File(Path, MD5Util.Md5(url).substring(0,10));
		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
		return bitmap;
	}
}
