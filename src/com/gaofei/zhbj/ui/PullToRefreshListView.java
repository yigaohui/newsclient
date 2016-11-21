package com.gaofei.zhbj.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gaofei.zhbj.R;

public class PullToRefreshListView extends ListView implements OnScrollListener{

	private View headView;
	private LinearLayout refrsh_root_container;
	private int measuredHeight;
	private int downY = -1;
	private static final int REFRESH = 0;
	private static final int REFRESHING = 1;
	private static final int PULL_DOWN = 2;
	private static int CURRENT = PULL_DOWN;
	private RotateAnimation up;
	private RotateAnimation down;
	private TextView mText;
	private ImageView mArrow;
	private ProgressBar mPb;
	private TextView mTime;
	private View footerView;
	private int measuredHeight2;
	private RefreshListener listener;
	boolean isLoadMore;
	private View myViewpager;
	public PullToRefreshListView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
		// TODO Auto-generated constructor stub
	}

	public PullToRefreshListView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public PullToRefreshListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initView();
		initAnimation();
		setOnScrollListener(this);
		// TODO Auto-generated constructor stub
	}

	private void initView() {
		headView = View.inflate(getContext(), R.layout.refresh_header, null);
		footerView = View.inflate(getContext(), R.layout.refresh_footer, null);
		refrsh_root_container = (LinearLayout) headView
				.findViewById(R.id.refresh_ll_headerrootview);
		mText = (TextView) headView.findViewById(R.id.refresh_tv_text);
		mArrow = (ImageView) headView.findViewById(R.id.refresh_iv_arrow);
		mPb = (ProgressBar) headView.findViewById(R.id.refresh_pb_loading);
		mTime = (TextView) headView.findViewById(R.id.refresh_tv_time);
		refrsh_root_container.measure(0, 0);
		measuredHeight = refrsh_root_container.getMeasuredHeight();
		refrsh_root_container.setPadding(0, -measuredHeight, 0, 0);
		footerView.measure(0, 0);
		measuredHeight2 = footerView.getMeasuredHeight();
		footerView.setPadding(0, 0, 0, -measuredHeight2);
		addHeaderView(headView);
		addFooterView(footerView);

	}

	public void setViewPager(View view) {
		myViewpager = view;
		refrsh_root_container.addView(view);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (downY == -1) {
				downY = (int) ev.getY();
			}
			int []listViewLocation=new int[2];
			getLocationOnScreen(listViewLocation);
			int listviewY=listViewLocation[1];
			int []viewPagerLocation =new int [2];
			myViewpager.getLocationOnScreen(viewPagerLocation);
			int viewpagerY=viewPagerLocation[1];
			if (listviewY > viewpagerY) {
				downY = -1;
				break;
			}
			int moveY = (int) ev.getY();
			int distanceY = moveY - downY;

			if (distanceY > 0 && getFirstVisiblePosition() == 0) {
				int paddingTop = distanceY - measuredHeight;
				if (paddingTop > 0 && CURRENT == PULL_DOWN) {
					// 显示松开刷新
					CURRENT = REFRESH;
					switchOption();
				} else if (paddingTop < 0 && CURRENT == REFRESH) {
					// 显示下拉刷新
					CURRENT = PULL_DOWN;
					switchOption();
				}
				refrsh_root_container.setPadding(0, paddingTop, 0, 0);
				return true;
			}

			break;

		case MotionEvent.ACTION_UP:
			if (CURRENT == REFRESH) {
				CURRENT = REFRESHING;
				
				refrsh_root_container.setPadding(0, 0, 0, 0);
				switchOption();
				listener.refresh();
			} else if (CURRENT == PULL_DOWN) {
				refrsh_root_container.setPadding(0, -measuredHeight, 0, 0);
				
			}

			downY = -1;
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);

	}

	private void switchOption() {
		switch (CURRENT) {
		case PULL_DOWN:
			mText.setText("下拉刷新");
			mArrow.startAnimation(down);
			break;
		case REFRESH:
			mText.setText("松开刷新");
			mArrow.startAnimation(up);
			break;
		case REFRESHING:
			mText.setText("正在刷新");
			mArrow.clearAnimation();
			mArrow.setVisibility(View.GONE);
			mPb.setVisibility(View.VISIBLE);
			getTime();
			break;
		}
	}

	private void initAnimation() {
		up = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		up.setDuration(500);
		up.setFillAfter(true);// 保持动画结束的状态
		down = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		down.setDuration(500);
		down.setFillAfter(true);// 保持动画结束的状态
	}

	private void getTime() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String time = simpleDateFormat.format(date);
		mTime.setText(time);
	}
	
	
	
	//创建上拉刷新和下拉刷新的回调监听
	public interface RefreshListener {
		//执行下拉刷新的操作
		public void refresh();
		//执行上拉加载更多的操作
		public void loadMore();
	}

	public void setOnRefreshListener(RefreshListener listener) {
		this.listener=listener;
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		if(scrollState==OnScrollListener.SCROLL_STATE_IDLE && getLastVisiblePosition()==getCount()-1 && isLoadMore==false) {
			footerView.setPadding(0, 0, 0, 0);
			listener.loadMore();
			isLoadMore=true;
		}
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}
	
	//刷新或加载更多完成,调用这个方法结束刷新的状态并且隐藏刷新头和加载更多的view
	public void finish(){
		if(CURRENT==REFRESHING) {
			refrsh_root_container.setPadding(0, -measuredHeight, 0,0);
			mText.setText("下拉刷新");
			mPb.setVisibility(View.GONE);
			mArrow.setVisibility(View.VISIBLE);
			CURRENT=PULL_DOWN;
		} if(isLoadMore) {
			footerView.setPadding(0, 0, 0, -measuredHeight2);
			isLoadMore=false;
		}
	}

}
