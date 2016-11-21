package com.gaofei.zhbj.pager.menu;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gaofei.zhbj.R;
import com.gaofei.zhbj.base.BaseMenuPager;
import com.gaofei.zhbj.bean.PhotosInfo;
import com.gaofei.zhbj.bean.PhotosInfo.PhotosItem;
import com.gaofei.zhbj.net.NetUrl;
import com.gaofei.zhbj.utils.BitMapUtil;
import com.gaofei.zhbj.utils.CacheMapUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NewsCenterMenuPhotosPager extends BaseMenuPager implements OnClickListener{

	private ListView mPhotos_listView;
	private GridView mPhotes_gridView;
	private List<PhotosItem> news;
	private ImageButton list2grid;
	private boolean isChange =true;
	private CacheMapUtil cacheMapUtil=new CacheMapUtil();
	public NewsCenterMenuPhotosPager(Activity activity,ImageButton list2grid) {
		
		super(activity);
		this.list2grid=list2grid;
		this.list2grid.setVisibility(View.VISIBLE);
		list2grid.setOnClickListener(this);
		
	}

	@Override
	public View initView() {
		contentView = View.inflate(activity, R.layout.newscnternewsphotoslist, null);
		mPhotos_listView = (ListView) contentView.findViewById(R.id.photos_list_lv);
		mPhotes_gridView = (GridView) contentView.findViewById(R.id.photos_list_gv);
	
		
		return contentView;
		
	}

	@Override
	public void initData() {
		
		HttpUtils httpUtils=new HttpUtils(2000);
		httpUtils.send(HttpMethod.GET, NetUrl.PHOTOURL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				System.out.println("ÁªÍø³É¹¦");
				
				processJson(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	protected void processJson(String json) {
		Gson gson=new Gson();
		PhotosInfo photoinfo = gson.fromJson(json, PhotosInfo.class);
		
		news = photoinfo.data.news;
		if(news.size()>0) {
			mPhotos_listView.setAdapter(new MyAdapter());
			mPhotes_gridView.setAdapter(new MyAdapter());
		}
		
	}
	
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return news.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return news.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view;
			viewHolder viewholder;
			if(convertView==null) {
				view = View.inflate(activity, R.layout.photos_item, null);
				viewholder=new viewHolder();
				viewholder.icon=(ImageView) view.findViewById(R.id.photoslist_icon);
				viewholder.textview=(TextView) view.findViewById(R.id.photoslist_textview);
				view.setTag(viewholder);
				
			} else {
				view=convertView;
				viewholder = (viewHolder) view.getTag();
			}
			
				
				viewholder.textview.setText(news.get(position).title);
				cacheMapUtil.display(news.get(position).listimage, viewholder.icon);
			
			return view;
		}
		
	}
	private class viewHolder {
		ImageView icon;
		TextView textview;
	}
	@Override
	public void onClick(View v) {
		if(isChange) {
			this.mPhotos_listView.setVisibility(View.GONE);
			this.mPhotes_gridView.setVisibility(View.VISIBLE);
			list2grid.setBackgroundResource(R.drawable.icon_pic_grid_type);
			isChange=false;
			
		} else {
			this.mPhotos_listView.setVisibility(View.VISIBLE);
			this.mPhotes_gridView.setVisibility(View.GONE);
			list2grid.setBackgroundResource(R.drawable.icon_pic_list_type);
			isChange=true;
		}
	}

}
