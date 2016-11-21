package com.gaofei.zhbj.fragment;

import java.util.ArrayList;
import java.util.List;

import com.gaofei.zhbj.HomeActivity;
import com.gaofei.zhbj.R;
import com.gaofei.zhbj.base.BaseFragment;
import com.gaofei.zhbj.base.BasePager;
import com.gaofei.zhbj.pager.GovPager;
import com.gaofei.zhbj.pager.HomePager;
import com.gaofei.zhbj.pager.NewsPager;
import com.gaofei.zhbj.pager.SettingPager;
import com.gaofei.zhbj.pager.SmartServicePager;
import com.gaofei.zhbj.ui.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class HomeFragment extends BaseFragment {

	private NoScrollViewPager mViewPager;
	private List<BasePager> list = new ArrayList<BasePager>();
	private RadioGroup mSelectBar;
	private MyAdapter myAdapter;

	@Override
	public View initFragment() {
		View view = View.inflate(getActivity, R.layout.homefragment, null);
		mViewPager = (NoScrollViewPager) view
				.findViewById(R.id.homefragment_vp);
		mSelectBar = (RadioGroup) view
				.findViewById(R.id.basepager_rg_selectbar);
		return view;
	}

	public NewsPager getNewsCenterPager(){
		return (NewsPager)list.get(1);
	}
	
	@Override
	public void initData() {
		list.add(new HomePager(getActivity));
		list.add(new NewsPager(getActivity));
		list.add(new GovPager(getActivity));
		list.add(new SmartServicePager(getActivity));
		list.add(new SettingPager(getActivity));
		if (myAdapter == null) {
			myAdapter = new MyAdapter();
			mViewPager.setAdapter(myAdapter);
		} else {
			myAdapter.notifyDataSetChanged();
		}
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			

			@Override
			public void onPageSelected(int position) {
				list.get(position).initData();
				if(position==0 ||position==list.size()-1) {
					
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				} else {
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
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
		list.get(0).initData();
		mViewPager.setCurrentItem(0);
		System.out.println(mViewPager.getCurrentItem());
		mSelectBar.check(R.id.homefragment_selectbar_rbtn_home);
		mSelectBar.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.homefragment_selectbar_rbtn_home:
					mViewPager.setCurrentItem(0,false);
					break;
				case R.id.homefragment_selectbar_rbtn_newscenter:
					mViewPager.setCurrentItem(1,false);
					break;

				case R.id.homefragment_selectbar_rbtn_smartservice:
					mViewPager.setCurrentItem(2,false);
					break;

				case R.id.homefragment_selectbar_rbtn_gov:
					mViewPager.setCurrentItem(3,false);
					break;

				case R.id.homefragment_selectbar_rbtn_setting:
					mViewPager.setCurrentItem(4,false);
					break;

				}
			}
		});

	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			BasePager basePager = list.get(position);
			View pagerView = basePager.view;
			container.addView(pagerView);
			return pagerView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}

	}

}
