package com.gaofei.zhbj.pager.menu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.gaofei.zhbj.HomeActivity;
import com.gaofei.zhbj.R;
import com.gaofei.zhbj.base.BaseMenuPager;
import com.gaofei.zhbj.bean.NewsCenterInfo;
import com.gaofei.zhbj.bean.NewsCenterInfo.newsInfoChild;
import com.gaofei.zhbj.pager.menu.newscenter.news.item.NewsCenterNewsItemPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

public class NewsCenterMenuNewsPager extends BaseMenuPager {

	private TabPageIndicator mIndicator;
	private ViewPager mViewPager;
	private newsInfoChild Child;
	private MyAdapter adapter;
	private List <NewsCenterNewsItemPager> newsItems;
	private ImageButton mNext;
	public NewsCenterMenuNewsPager(Activity activity,newsInfoChild Child) {
		super(activity);
		this.Child=Child;

	}

	@Override
	public View initView() {
		contentView = View.inflate(activity, R.layout.newscentermenunewspager,
				null);
		mNext = (ImageButton) contentView.findViewById(R.id.menunewscenter_ibtn_next);
		
		return contentView;

	}
	@Override
	public void initData() {
		newsItems=new ArrayList<NewsCenterNewsItemPager>();
		mIndicator = (TabPageIndicator) contentView
				.findViewById(R.id.newscenter_menunews_indicator);
		mViewPager = (ViewPager) contentView
				.findViewById(R.id.newscenter_menunews_pager);
	
		for (int i = 0; i < Child.children.size(); i++) {
			NewsCenterNewsItemPager newsItem = new NewsCenterNewsItemPager(activity, Child.children.get(i).url);
			newsItems.add(newsItem);
		}
		
		if(adapter==null) {
			adapter = new MyAdapter();
			mViewPager.setAdapter(adapter);
		}
		else {
			adapter.notifyDataSetChanged();
		}
		mIndicator.setViewPager(mViewPager);
		
		mIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				if(position ==0) {
					((HomeActivity)activity).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

				} else {
					((HomeActivity)activity).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
			}
		});
	}


	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Child.children.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}
		

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			NewsCenterNewsItemPager newsItemPager = newsItems.get(position);
			View view = newsItemPager.contentView;
			newsItemPager.initData();
			container.addView(view);
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		public CharSequence getPageTitle(int position) {
			return Child.children.get(position).title;
		}
		
		
	}


}
