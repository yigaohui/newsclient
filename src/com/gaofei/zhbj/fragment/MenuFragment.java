package com.gaofei.zhbj.fragment;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gaofei.zhbj.HomeActivity;
import com.gaofei.zhbj.R;
import com.gaofei.zhbj.base.BaseFragment;

public class MenuFragment extends BaseFragment {

	private ListView mListView;
	private List<String> titles;
	private int currentposition =0;
	private MyAdapter myAdapter;
	@Override
	public View initFragment() {

		view = View.inflate(getActivity, R.layout.menu_fragment, null);
		return view;
	}

	@Override
	public void initData() {
		mListView = (ListView) view.findViewById(R.id.menufragment_lv);

	}

	public void initTitles(List<String> titles) {
		this.titles=titles;
		if(myAdapter==null) {
			myAdapter = new MyAdapter();
			mListView.setAdapter(myAdapter);
		}
		else {
			myAdapter.notifyDataSetChanged();
		}
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentposition=position;
				myAdapter.notifyDataSetChanged();
				slidingMenu.toggle();
				((HomeActivity)getActivity).getHomeFragment().getNewsCenterPager().switchMenuPager(position);
				
			}
		});
		mListView.setSelection(0);
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return titles.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View v;
			ViewHolder viewHolder;
			if (convertView == null) {
				v = View.inflate(getActivity, R.layout.menu_listview_item, null);
				viewHolder = new ViewHolder();
				viewHolder.mArrow = (ImageView) v.findViewById(R.id.item_iv_arrow);
				viewHolder.mTitle = (TextView) v.findViewById(R.id.item_tv_title);
				v.setTag(viewHolder);
			}else{
				v = convertView;
				viewHolder = (ViewHolder) v.getTag();
			} if(currentposition==position) {
				viewHolder.mArrow.setImageResource(R.drawable.menu_arr_select);
				viewHolder.mTitle.setTextColor(Color.RED);
			} else {
				viewHolder.mArrow.setImageResource(R.drawable.menu_arr_normal);
				viewHolder.mTitle.setTextColor(Color.WHITE);
			}
			viewHolder.mTitle.setText(titles.get(position));
			return v;
		}
		
	}
	
	static class ViewHolder{
		TextView mTitle;
		ImageView mArrow;
	}}
