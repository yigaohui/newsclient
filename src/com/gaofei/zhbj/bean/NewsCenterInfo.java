package com.gaofei.zhbj.bean;

import java.util.List;

public class NewsCenterInfo {

	public List<newsInfoChild> data;
	public List<String> extend;
	public String retcode;
	
	public class newsInfoChild {
		public List<Children> children;
		public String id;
		public String title;
		public String type;
		
		public String url1;
		public String url;
		
		public String dayurl;
		public String excurl;
		public String weekurl;
		@Override
		public String toString() {
			return "newsInfoChild [children=" + children + ", id=" + id
					+ ", title=" + title + ", type=" + type + ", url1=" + url1
					+ ", url=" + url + ", dayurl=" + dayurl + ", excurl="
					+ excurl + ", weekurl=" + weekurl + "]";
		}
		
	}
	public class Children {
		public String id;
		public String title;
		public String type;
		public String url;
		@Override
		public String toString() {
			return "Children [id=" + id + ", title=" + title + ", type=" + type
					+ ", url=" + url + "]";
		}
		
		
	}
	@Override
	public String toString() {
		return "NewsCenterInfo [data=" + data + ", extend=" + extend
				+ ", retcode=" + retcode + "]";
	}
	
}
