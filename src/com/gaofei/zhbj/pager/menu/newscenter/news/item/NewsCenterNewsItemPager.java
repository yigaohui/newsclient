package com.gaofei.zhbj.pager.menu.newscenter.news.item;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioRecord.OnRecordPositionUpdateListener;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaofei.zhbj.NewsDetailActivity;
import com.gaofei.zhbj.R;
import com.gaofei.zhbj.base.BaseMenuPager;
import com.gaofei.zhbj.bean.NewTabInfo;
import com.gaofei.zhbj.bean.NewTabInfo.NewsInfo;
import com.gaofei.zhbj.bean.NewsCenterInfo.Children;
import com.gaofei.zhbj.net.NetUrl;
import com.gaofei.zhbj.ui.PullToRefreshListView;
import com.gaofei.zhbj.ui.PullToRefreshListView.RefreshListener;
import com.gaofei.zhbj.ui.RollViewPager;
import com.gaofei.zhbj.utils.SharedPrefrencesUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

public class NewsCenterNewsItemPager extends BaseMenuPager {

	private View mListViewView;
	private View mViewPagerView;
	private List<String> imaUrls = new ArrayList<String>();

	private List<String> titles = new ArrayList<String>();
	private String mUrl;
	private TextView mTitle;
	private RollViewPager mViewPager;
	private MyAdapter adapter;
	private CirclePageIndicator mIndicator;
	private PullToRefreshListView mListView;
	private Handler handler;
	private List<NewsInfo> news = new ArrayList<NewTabInfo.NewsInfo>();
	private String moreUrl;
	private MyBaseAdapter myBaseAdapter;
	private List<String> readeds=new ArrayList<String> ();
	public NewsCenterNewsItemPager(Activity activity, String url) {
		super(activity);
		this.mUrl = url;
	}

	@Override
	public View initView() {

		mViewPagerView = (View) View.inflate(activity,
				R.layout.newscenter_news_viewpager, null);
		mViewPager = (RollViewPager) mViewPagerView
				.findViewById(R.id.newscenter_news_viewpager);
		mTitle = (TextView) mViewPagerView
				.findViewById(R.id.newscenter_news_viewpager_title);
		mIndicator = (CirclePageIndicator) mViewPagerView
				.findViewById(R.id.newscenter_news_indicator);

		mListViewView = (View) View.inflate(activity,
				R.layout.newscenter_news_listview, null);
		mListView = (PullToRefreshListView) mListViewView
				.findViewById(R.id.newscenter_news_listview);
		mListView.setViewPager(mViewPagerView);
		mListView.setOnItemClickListener(new MyOnItemClickListener());
		mListView.setOnRefreshListener(new MyRefreshListener());
		return mListViewView;

	}
	private class MyOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			boolean isread = news.get(position-1).isread;
			if(isread==false) {
				news.get(position-1).isread=true;
				myBaseAdapter.notifyDataSetChanged();
				String sp_isread = SharedPrefrencesUtil.getString(activity, "isread", "");
				SharedPrefrencesUtil.saveString(activity, "isread",sp_isread+"#"+news.get(position-1).id);
				
			} 

			Intent intent=new Intent(activity, NewsDetailActivity.class);
			intent.putExtra("url", news.get(position-1).url);
			activity.startActivity(intent);
		}
		
	}

	private class MyRefreshListener implements RefreshListener {

		@Override
		public void refresh() {

			getData(mUrl, false);

		}

		@Override
		public void loadMore() {
			getData(moreUrl, true);
		}

	}

	@Override
	public void initData() {
		getData(mUrl, false);
	}

	private void getData(String url, final boolean isLoadMore) {

		String readed = SharedPrefrencesUtil.getString(activity, "isread", "");
		
		if(!TextUtils.isEmpty(readed)) {
			String []readed_ids = readed.split("#");
			for (int i = 0; i < readed_ids.length; i++) {
				readeds.add(readed_ids[i]);
			}
		}
		
		if (!TextUtils.isEmpty(url)) {
			System.out.println(NetUrl.NEWSCENTERURL + url);
			HttpUtils httpUtils = new HttpUtils(2000);
			httpUtils.send(HttpMethod.GET, NetUrl.SERVERURL + url,
					new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							System.out.println("联网成功");
							String result = responseInfo.result;

							processJson(result, isLoadMore);
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							System.out.println("联网失败");
						}
					});
		} else {
			Toast.makeText(activity, "没有更多数据了", 0).show();
			mListView.finish();
		}

	}

	protected void processJson(String result, boolean isLoadMore) {
		Gson gson = new Gson();
		NewTabInfo newTabInfo = gson.fromJson(result, NewTabInfo.class);

		initViewPager(newTabInfo, isLoadMore);
	}

	private void initViewPager(NewTabInfo newTabInfo, boolean isLoadMore) {

		
		news.clear();
		moreUrl = newTabInfo.data.more;
		if (!isLoadMore) {
			if (newTabInfo.data.topnews.size() > 0) {
				imaUrls.clear();
				titles.clear();
				for (int j = 0; j < newTabInfo.data.topnews.size(); j++) {
					imaUrls.add(newTabInfo.data.topnews.get(j).topimage);
					titles.add(newTabInfo.data.topnews.get(j).title);
				}

				if (adapter == null) {
					adapter = new MyAdapter();
					mViewPager.setAdapter(adapter);
				} else {
					adapter.notifyDataSetChanged();
				}

				// if (mListView.getHeaderViewsCount() < 1) {
				// mListView.addHeaderView(mViewPagerView);//
				// 将viewPager的布局添加到listview中
				// }
				mTitle.setText(titles.get(0));
				mIndicator.setViewPager(mViewPager);

				mIndicator.onPageSelected(0);
				mViewPager.setCurrentItem(0);
				if (handler == null) {
					handler = new Handler() {
						public void handleMessage(android.os.Message msg) {
							int currentItem = mViewPager.getCurrentItem();
							if (currentItem == imaUrls.size() - 1) {
								currentItem = 0;
							} else {
								currentItem++;
							}

							mViewPager.setCurrentItem(currentItem);
							handler.sendEmptyMessageDelayed(0, 2000);
						};
					};
					handler.sendEmptyMessageDelayed(0, 2000);
				}
				mIndicator.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						mTitle.setText(titles.get(position));
					}

					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int state) {
						// TODO Auto-generated method stub

					}
				});
			}
		}
		if (newTabInfo.data.news.size() > 0) {
			if(!isLoadMore) {
				news=newTabInfo.data.news;
			}else {
				news.addAll(newTabInfo.data.news);
			}
			for (NewsInfo newsinfo : newTabInfo.data.news) {
				if(readeds.contains(newsinfo.id)) {
					newsinfo.isread=true;
				} else {
					newsinfo.isread=false;
				}
			}
			

		}
		if (myBaseAdapter == null) {
			myBaseAdapter = new MyBaseAdapter();
			mListView.setAdapter(myBaseAdapter);
			System.out.println("setdatapter");
		} else {
			myBaseAdapter.notifyDataSetChanged();
		}

		// 加载完数据,取消刷新操作
		mListView.finish();

	}

	private class MyBaseAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return news.size();
		}

		@Override
		public Object getItem(int position) {
			return news.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = View.inflate(activity,
						R.layout.menunewscenteritem_listview_item, null);
			} else {
				view = convertView;
			}
			View mIcon = view.findViewById(R.id.item_iv_icon);
			TextView mTitle = (TextView) view.findViewById(R.id.item_tv_title);
			TextView mTime = (TextView) view.findViewById(R.id.item_tv_time);
			BitmapUtils bitmapUtils = new BitmapUtils(activity);
			NewsInfo info = news.get(position);
			bitmapUtils.display(mIcon, info.listimage);
			
			mTitle.setText(info.title);
			mTime.setText(info.pubdate);
			
			if(info.isread) {
				mTitle.setTextColor(Color.GRAY);
			} else {
				mTitle.setTextColor(Color.BLACK);
			}
			return view;
		}

	}

	private class MyAdapter extends PagerAdapter {

		private ImageView mIcon;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imaUrls.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			View view = View.inflate(activity,
					R.layout.menunewscenteritem_viewpager_item, null);
			mIcon = (ImageView) view
					.findViewById(R.id.menunewscenteritem_viewpageritem_iv_icon);
			BitmapUtils bitmapUtils = new BitmapUtils(activity);
			String imageuri = imaUrls.get(position);
			bitmapUtils.display(mIcon, imageuri);

			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						handler.removeCallbacksAndMessages(null);
						break;
					case MotionEvent.ACTION_UP:
						handler.sendEmptyMessageDelayed(0, 2000);
						break;
					case MotionEvent.ACTION_CANCEL:
						handler.sendEmptyMessageDelayed(0, 2000);
						break;

					default:
						break;
					}
					return true;
				}
			});
			container.addView(view);

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}
	}

}
