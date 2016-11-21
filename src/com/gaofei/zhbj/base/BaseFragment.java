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
	//��ʼ������
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActivity=getActivity();
		slidingMenu = ((HomeActivity) getActivity).getSlidingMenu();
		super.onCreate(savedInstanceState);
	}
	//����fragment����
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = initFragment();
		return view;
	}
	//������ʾ����
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initData();
		super.onActivityCreated(savedInstanceState);
	}
	public abstract View initFragment();
	public abstract void initData();
	
}
