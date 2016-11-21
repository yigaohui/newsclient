package com.gaofei.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.gaofei.zhbj.base.BasePager;

public class GovPager extends BasePager {

	public GovPager(Activity activity) {
		super(activity);
		
		
	}

	@Override
	public void initData() {
		
		mTitle.setText("¹ÄÀø¶þÌ¥");
		TextView textView = new TextView(activity);
		textView.setText("ÕþÎñ");
		textView.setTextColor(Color.RED);
		textView.setTextSize(20);
		textView.setGravity(Gravity.CENTER);
		mContainer.addView(textView);
		super.initData();
	}
	
	

}
