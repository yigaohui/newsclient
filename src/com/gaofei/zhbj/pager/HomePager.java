package com.gaofei.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gaofei.zhbj.base.BasePager;

public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
		
		
	}

	@Override
	public void initData() {
		
		mTitle.setText("Ê×Ò³");
		TextView textView = new TextView(activity);
		textView.setText("Ê×Ò³");
		textView.setTextColor(Color.RED);
		textView.setTextSize(20);
		textView.setGravity(Gravity.CENTER);
		mContainer.addView(textView);
		mMenu.setVisibility(View.GONE);
		super.initData();
	}
	
	

}
