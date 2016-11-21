package com.gaofei.zhbj.bean;

import java.util.List;

public class PhotosInfo {

	public Photos data;
	public String retcode;
	public class Photos{
		public String countcommenturl;
		public String more;
		public List<PhotosItem> news;
		public String title;
		public List topic;
		
	}
	public class PhotosItem{
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public String id;
		public String largeimage;
		public String listimage;
		public String pubdate;
		public String smallimage;
		public String title;
		public String type;
		public String url;
	}
}
