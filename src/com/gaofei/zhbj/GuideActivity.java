package com.gaofei.zhbj;



import java.util.ArrayList;
import java.util.List;

import com.gaofei.zhbj.utils.Constants;
import com.gaofei.zhbj.utils.SharedPrefrencesUtil;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;

public class GuideActivity extends Activity implements OnClickListener{

	private ViewPager mViewPager;
	private MyAdapter adapter;
	private int[] mImageIds = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	private List <ImageView> imageVeiwList =new ArrayList<ImageView>();
	private Button mStart;
	private LinearLayout mLl_Dots;
	private ImageView mRedDot;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		initView();
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.guide_vp_image);
		mStart = (Button) findViewById(R.id.guide_btn_start);
		mLl_Dots = (LinearLayout) findViewById(R.id.guide_ll_dots);
		mRedDot = (ImageView) findViewById(R.id.guide_iv_readdot);
		createImageViewAndDots();
		adapter = new MyAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
		mStart.setOnClickListener(this);
	}
	private class MyPageChangeListener implements OnPageChangeListener {

		private RelativeLayout.LayoutParams layoutParams;

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			layoutParams = (android.widget.RelativeLayout.LayoutParams) mRedDot.getLayoutParams();
			layoutParams.leftMargin=(int) (position*20+positionOffset*20);
			mRedDot.setLayoutParams(layoutParams);
		}

		@Override
		public void onPageSelected(int position) {
			if(position==imageVeiwList.size()-1) {
				mStart.setVisibility(View.VISIBLE);
			} else {
				mStart.setVisibility(View.GONE);
			}
			
			
		}

		@Override
		public void onPageScrollStateChanged(int state) {

			
		}
		
	}

	private void createImageViewAndDots() {
		for (int i = 0; i < mImageIds.length; i++) {
			createImageVeiw(i);
			createDots(i);
		}
	}

	private void createDots(int i) {
		View dot=new View(this);
		dot.setBackgroundResource(R.drawable.shape_guide_dot);
		LinearLayout.LayoutParams params=new LayoutParams(10, 10);
		params.rightMargin=10;
		dot.setLayoutParams(params);
		
		mLl_Dots.addView(dot);
	}

	private void createImageVeiw(int i) {
		ImageView imageview=new ImageView(this);
		imageview.setBackgroundResource(mImageIds[i]);
		imageVeiwList.add(imageview);
	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageVeiwList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = imageVeiwList.get(position);
			container.addView(imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//super.destroyItem(container, position, object);
			container.removeView((View)object);
		}
		
		
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.guide_btn_start) {
			SharedPrefrencesUtil.saveBoolean(this, Constants.ISFIRSTENTER, false);
			Intent intent=new Intent(this, HomeActivity.class);
			startActivity(intent);
			finish();
		}
	}
}
