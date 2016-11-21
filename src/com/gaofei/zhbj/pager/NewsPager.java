package com.gaofei.zhbj.pager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gaofei.zhbj.HomeActivity;
import com.gaofei.zhbj.base.BaseMenuPager;
import com.gaofei.zhbj.base.BasePager;
import com.gaofei.zhbj.bean.NewsCenterInfo;
import com.gaofei.zhbj.net.NetUrl;
import com.gaofei.zhbj.pager.menu.NewsCenterMenuActionPager;
import com.gaofei.zhbj.pager.menu.NewsCenterMenuNewsPager;
import com.gaofei.zhbj.pager.menu.NewsCenterMenuPhotosPager;
import com.gaofei.zhbj.pager.menu.NewsCenterMenuSpecialPager;
import com.gaofei.zhbj.utils.SharedPrefrencesUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NewsPager extends BasePager {
	private List<String> titles;
	private List<BaseMenuPager> pagers;
	public NewsPager(Activity activity) {
		super(activity);
		
		
	}

	@Override
	public void initData() {
		
//		mTitle.setText("新闻");
//		TextView textView = new TextView(activity);
//		textView.setText("新闻");
//		textView.setTextColor(Color.RED);
//		textView.setTextSize(20);
//		textView.setGravity(Gravity.CENTER);
		mMenu.setVisibility(View.VISIBLE);
		
		String newsCache = SharedPrefrencesUtil.getString(activity, "news", "");
		if(!TextUtils.isEmpty(newsCache)) {
			processJson(newsCache);
		}
		getData();
		super.initData();
	}

	private void getData() {
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpMethod.GET, NetUrl.NEWSCENTERURL, new RequestCallBack<String>() {


			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=responseInfo.result;
				SharedPrefrencesUtil.saveString(activity, "news", result);
				processJson(result);
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("请求失败");
				
			}

			
		} );
	}

	protected void processJson(String result) {
		Gson gson=new Gson();
		NewsCenterInfo newsCenterInfo = gson.fromJson(result, NewsCenterInfo.class);
		
		setMenuFragmentMsg(newsCenterInfo);
	}

	private void setMenuFragmentMsg(NewsCenterInfo newsCenterInfo) {
		titles = new ArrayList<String>();
		titles.clear();
		for (int i = 0; i < newsCenterInfo.data.size(); i++) {
			titles.add(newsCenterInfo.data.get(i).title);
		}
		((HomeActivity)activity).getMenuFragment().initTitles(titles);
		pagers=new ArrayList<BaseMenuPager>();
		pagers.add(new NewsCenterMenuNewsPager(activity,newsCenterInfo.data.get(0)));
		pagers.add(new NewsCenterMenuSpecialPager(activity));
		pagers.add(new NewsCenterMenuPhotosPager(activity,mList2Grid));
		pagers.add(new NewsCenterMenuActionPager(activity));
		
		switchMenuPager(0);
		
	}
	public void switchMenuPager(int position){
		BaseMenuPager baseMenuPager = pagers.get(position);
		mTitle.setText(titles.get(position));
		mContainer.removeAllViews();
		mContainer.addView(baseMenuPager.contentView);
		baseMenuPager.initData();
	}

}
