package com.gaofei.zhbj.base;

import com.gaofei.zhbj.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	
	public Activity getActivity;
	public View view;
	public SlidingMenu slidingMenu;
	//初始化数据
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActivity=getActivity();
		slidingMenu = ((HomeActivity) getActivity).getSlidingMenu();
		super.onCreate(savedInstanceState);
	}
	//加载fragment布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = initFragment();
		return view;
	}
	//加载显示数据
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initData();
		super.onActivityCreated(savedInstanceState);
	}
	public abstract View initFragment();
	public abstract void initData();
	
}
