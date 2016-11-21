package com.gaofei.zhbj.pager.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gaofei.zhbj.R;
import com.gaofei.zhbj.base.BaseMenuPager;

public class NewsCenterMenuSpecialPager extends BaseMenuPager{

	public NewsCenterMenuSpecialPager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		TextView textView = new TextView(activity);
		textView.setText("菜单详情页-专题");
		textView.setTextSize(22);
		textView.setTextColor(Color.RED);
		textView.setGravity(Gravity.CENTER);
		return textView;
		
	}

	@Override
	public void initData() {
		
		
	}

}
