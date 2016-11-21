package com.gaofei.zhbj;

import com.gaofei.zhbj.fragment.HomeFragment;
import com.gaofei.zhbj.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;

public class HomeActivity extends SlidingFragmentActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initSlidingMenu();
		initView();
	}

	private void initView() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.home_root, new HomeFragment(), "Home");
		transaction.replace(R.id.menu_root, new MenuFragment(), "Menu");
		transaction.commit();
	}

	private void initSlidingMenu() {
		SlidingMenu slidingMenu=getSlidingMenu();
		//����slidingmenu����ʾģʽ
		slidingMenu.setMode(SlidingMenu.LEFT);
		//���ò˵��Ĵ���ģʽ
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		//ָ�������˵��Ĳ����ļ�
		setBehindContentView(R.layout.menu);
		//���ò����˵��Ŀ��
		slidingMenu.setBehindWidth(120);
		//���ò����˵�����ҳ�Ŀ��
		//slidingMenu.setBehindOffset(220);
		slidingMenu.setShadowDrawable(R.drawable.shape_slidingmenu_divirer);
		slidingMenu.setShadowWidth(5);
	}

	public MenuFragment getMenuFragment(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		MenuFragment menuFragment = (MenuFragment) fragmentManager.findFragmentByTag("Menu");
		return menuFragment;
		
	}
	public HomeFragment getHomeFragment(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag("Home");
		return homeFragment;
		
	}
}
