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
		//设置slidingmenu的显示模式
		slidingMenu.setMode(SlidingMenu.LEFT);
		//设置菜单的触摸模式
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		//指定侧拉菜单的布局文件
		setBehindContentView(R.layout.menu);
		//设置侧拉菜单的宽度
		slidingMenu.setBehindWidth(120);
		//设置侧拉菜单内容页的宽度
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
