package com.gaofei.zhbj.pager.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gaofei.zhbj.R;
import com.gaofei.zhbj.base.BaseMenuPager;

public class NewsCenterMenuActionPager extends BaseMenuPager{

	public NewsCenterMenuActionPager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		TextView textView = new TextView(activity);
		textView.setText("≤Àµ•œÍ«È“≥-ª•∂Ø");
		textView.setTextSize(22);
		textView.setTextColor(Color.RED);
		textView.setGravity(Gravity.CENTER);
		return textView;
		
	}

	@Override
	public void initData() {
		
		
	}

}
