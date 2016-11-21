package com.gaofei.zhbj.base;

import android.app.Activity;
import android.view.View;

public abstract class  BaseMenuPager {

	public Activity activity;
	public View contentView;
	public BaseMenuPager (Activity activity){
		this.activity=activity;
		
		contentView=initView();
	}
	
	public abstract View initView();
	public abstract void initData();
}
