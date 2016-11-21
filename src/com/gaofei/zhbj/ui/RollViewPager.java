package com.gaofei.zhbj.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class RollViewPager extends ViewPager {

	private int downX;
	private int downY;

	public RollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RollViewPager(Context context) {
		super(context);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = (int) ev.getX();
			downY = (int) ev.getY();
			getParent().requestDisallowInterceptTouchEvent(true);
			
			break;
		case MotionEvent.ACTION_MOVE:

			int moveX = (int) ev.getX();
			int moveY = (int) ev.getY();
			int distanceX = moveX - downX;
			int distanceY = moveY - downY;
			if (Math.abs(distanceX) > Math.abs(distanceY)) {
				if (distanceX > 0 && getCurrentItem() == 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else if (distanceX > 0 && getCurrentItem() > 0) {
					getParent().requestDisallowInterceptTouchEvent(true);
				} else if (distanceX < 0
						&& getCurrentItem() == getAdapter().getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else if (distanceX < 0
						&& getCurrentItem() < getAdapter().getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(true);

				}
			} else {
				getParent().requestDisallowInterceptTouchEvent(false);
			}

			break;
		case MotionEvent.ACTION_UP:

			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

}
