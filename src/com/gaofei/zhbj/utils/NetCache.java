package com.gaofei.zhbj.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class NetCache {

	private LocalCache localcache;
	private MemoryCache memorycache;
	public NetCache (LocalCache cache ,MemoryCache cache2) {
		this.localcache=cache;
		this.memorycache=cache2;
	}
	public void getBitmap(ImageView image,String url){
		 new MyAsyncTask().execute(image,url);
	}
	
	private class MyAsyncTask extends AsyncTask<Object, String, Bitmap>  {
		
		

		private ImageView imageview;
		private String url;

		//�첽����֮ǰִ�еĲ���
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		//�첽����֮��ִ�еĲ���
		//�൱��runonuithread
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			String url = (String) imageview.getTag();
			if(url.endsWith(this.url)) {
				if(url!=null) {
					imageview.setImageBitmap(result);
					localcache.setBitMap(url, result);
					memorycache.setBitMap(url, result);
				} 
				
			}
			
			super.onPostExecute(result);
		}

		//���½��ȵĲ���,�൱����download�����е�loading
		
		//�첽����
		//�൱��new thread��run������ִ�еĲ���
		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			System.out.println("�������ȡͼƬ");
			imageview = (ImageView) params[0];
			url = (String) params[1];
			imageview.setTag(url);
			Bitmap bitmap = downLoadImage(url);
			return bitmap;
		}
		
	}
	
	
	public Bitmap downLoadImage(String url) {
		
		
		Bitmap bitmap=null;
		URL imageUrl;
		try {
			imageUrl = new URL(url);
			HttpURLConnection conn=(HttpURLConnection) imageUrl.openConnection();
			
			int responseCode = conn.getResponseCode();
			
			if(responseCode==200) {
				InputStream inputStream = conn.getInputStream();
				bitmap=BitmapFactory.decodeStream(inputStream);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bitmap;
	}
}
