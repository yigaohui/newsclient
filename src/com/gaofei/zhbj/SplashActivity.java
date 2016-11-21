package com.gaofei.zhbj;

import com.gaofei.zhbj.utils.Constants;
import com.gaofei.zhbj.utils.SharedPrefrencesUtil;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

    private RelativeLayout mRoot;
	private AnimationSet animationSet;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();
        initView();
        
       
    }

	private void setAnimation() {
		RotateAnimation rotateAnimation=new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(1000);
		rotateAnimation.setFillAfter(true);
		ScaleAnimation scaleAnimation=new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(1000);
		scaleAnimation.setFillAfter(true);
		AlphaAnimation alphaAnimation=new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(2000);
		alphaAnimation.setFillAfter(true);
		
		animationSet = new AnimationSet(false);
		
		animationSet.addAnimation(alphaAnimation);
		animationSet.addAnimation(scaleAnimation);
		animationSet.addAnimation(rotateAnimation);
		
	}

	private void initView() {
		mRoot = (RelativeLayout) findViewById(R.id.splash_rel_root);
		mRoot.startAnimation(animationSet);
		final Intent intent=new Intent(this,GuideActivity.class);
		final Intent intent2=new Intent(this,HomeActivity.class);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if(SharedPrefrencesUtil.getBoolean(SplashActivity.this, Constants.ISFIRSTENTER, true)) {
					startActivity(intent);
					finish();
				} else {
					startActivity(intent2);
					finish();
				}
				
			}
		}, 3000);
		
		
	}
    
}
