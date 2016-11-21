package com.gaofei.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.gaofei.zhbj.base.BasePager;

public class SmartServicePager extends BasePager {

	public SmartServicePager(Activity activity) {
		super(activity);
		
		
	}

	@Override
	public void initData() {
		
		mTitle.setText("生活");
		TextView textView = new TextView(activity);
		textView.setText("智能服务");
		textView.setTextColor(Color.RED);
		textView.setTextSize(20);
		textView.setGravity(Gravity.CENTER);
		mContainer.addView(textView);
		super.initData();
	}
	
	

}
