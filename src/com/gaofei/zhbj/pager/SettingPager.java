package com.gaofei.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gaofei.zhbj.base.BasePager;

public class SettingPager extends BasePager {

	public SettingPager(Activity activity) {
		super(activity);
		
		
	}

	@Override
	public void initData() {
		
		mTitle.setText("����");
		TextView textView = new TextView(activity);
		textView.setText("����");
		textView.setTextColor(Color.RED);
		textView.setTextSize(20);
		textView.setGravity(Gravity.CENTER);
		mContainer.addView(textView);
		mMenu.setVisibility(View.GONE);
		super.initData();
	}
	
	

}
