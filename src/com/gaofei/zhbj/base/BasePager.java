package com.gaofei.zhbj.base;

import com.gaofei.zhbj.HomeActivity;
import com.gaofei.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePager implements OnClickListener{
	
	public Activity activity;
	public View view;
	public TextView mTitle;
	public FrameLayout mContainer;
	public ImageButton mMenu;
	private SlidingMenu slidingMenu;
	public ImageButton mList2Grid;
	
	public BasePager(Activity activity){
		this.activity=activity;
		slidingMenu = ((HomeActivity)activity).getSlidingMenu();
		initView();
	}
	
	public View initView() {
		view = View.inflate(activity, R.layout.basepager, null);
		mTitle = (TextView) view.findViewById(R.id.mytitle_tv_title);
		mContainer = (FrameLayout) view.findViewById(R.id.basepager_fl_container);
		mMenu = (ImageButton) view.findViewById(R.id.mytitle_ibtn_menu);
		mList2Grid = (ImageButton) view.findViewById(R.id.mytitle_ibtn_list2grid);
		mMenu.setOnClickListener(this);
		return view;
	};
	
	public void initData(){
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.mytitle_ibtn_menu:
			slidingMenu.toggle();
			break;

		default:
			break;
		}
	};
}
