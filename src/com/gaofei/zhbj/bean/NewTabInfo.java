package com.gaofei.zhbj.bean;

import java.util.List;

public class NewTabInfo {

	public NewsItem data;
	public String retcode;
	
	
	@Override
	public String toString() {
		return "NewTabInfo [data=" + data + ", retcode=" + retcode + "]";
	}
	public class NewsItem{
		public String countcommenturl;
		public String more;
		public List<NewsInfo> news;
		public List<TopInfo> topic;
		public List<TopNews> topnews;
		public String title;
		@Override
		public String toString() {
			return "NewsItem [countcommenturl=" + countcommenturl + ", more="
					+ more + ", news=" + news + ", topic=" + topic
					+ ", topnews=" + topnews + ", title=" + title + "]";
		}
		
	
	
	}
	public class NewsInfo {
		public boolean comment;
		public String commentlist;
		public String id;
		public String commenturl;
		public String listimage;
		public String pubdate;
		public boolean isread;
		public String title;
		public String type;
		public String url;
		@Override
		public String toString() {
			return "NewsInfo [comment=" + comment + ", commentlist="
					+ commentlist + ", id=" + id + ", commenturl=" + commenturl
					+ ", listimage=" + listimage + ", pubdate=" + pubdate
					+ ", title=" + title + ", type=" + type + ", url=" + url
					+ "]";
		}
		
	}
	public class TopInfo {
		public String description;
		public int id;
		public String listimage;
		public String sort;
		public String title;
		public String url;
		@Override
		public String toString() {
			return "TopInfo [description=" + description + ", id=" + id
					+ ", listimage=" + listimage + ", sort=" + sort
					+ ", title=" + title + ", url=" + url + "]";
		}
		
	}
	public class TopNews {
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public int id;
		public String pubdate;
		
		public String title;
		public String topimage;
		public String type;
		public String url;
		@Override
		public String toString() {
			return "TopNews [comment=" + comment + ", commentlist="
					+ commentlist + ", commenturl=" + commenturl + ", id=" + id
					+ ", pubdate=" + pubdate + ", title=" + title
					+ ", topimage=" + topimage + ", type=" + type + ", url="
					+ url + "]";
		}
		
	}
}
