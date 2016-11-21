package com.gaofei.zhbj;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class NewsDetailActivity extends Activity implements OnClickListener {

	private String mUrl;
	private WebView mWebView;

	@ViewInject(R.id.mytitle_tv_title)
	private TextView mTitle;

	@ViewInject(R.id.mytitle_ibtn_back)
	private ImageButton mBack;

	@ViewInject(R.id.mytitle_ibtn_menu)
	private ImageButton mMenu;

	@ViewInject(R.id.titlebar_ll_shareandtextsize)
	private LinearLayout mShareAndTextsize;
	@ViewInject(R.id.titlebar_btn_textsize)
	private ImageButton mTextSize;
	@ViewInject(R.id.titlebar_btn_share)
	private ImageButton mShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		Intent intent = getIntent();
		mUrl = intent.getStringExtra("url");
		ViewUtils.inject(this);
		initView();
		initData();
	}

	private void initData() {
		mWebView.loadUrl(mUrl);
		settings = mWebView.getSettings();
		settings.setBuiltInZoomControls(true);
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);

	}

	private void initView() {

		mWebView = (WebView) findViewById(R.id.news_wv_webview);
		mBack.setVisibility(View.VISIBLE);
		mMenu.setVisibility(View.GONE);
		mTitle.setText("详情");
		mShareAndTextsize.setVisibility(View.VISIBLE);

		mBack.setOnClickListener(this);
		mTextSize.setOnClickListener(this);
		mShare.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mytitle_ibtn_back:
			finish();
			break;
		case R.id.titlebar_btn_textsize:
			shwoTextsizeDialog();
			break;
		case R.id.titlebar_btn_share:
			showShare();
			break;
		default:
			break;
		}
	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		oks.setTheme(OnekeyShareTheme.CLASSIC);
		
		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("标题");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		//oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/text.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite("ShareSDK");
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

	private int currentTextsize = 2;
	private String[] items = new String[] { "超大号字体", "大号字体", "正常字体", "小号字体",
			"超小号字体" };
	private WebSettings settings;

	private void shwoTextsizeDialog() {
		AlertDialog.Builder builder = new Builder(this);

		builder.setSingleChoiceItems(items, currentTextsize,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						currentTextsize = which;
					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (currentTextsize) {
				case 0:
					settings.setTextSize(TextSize.LARGEST);
					break;
				case 1:
					settings.setTextSize(TextSize.LARGER);

					break;
				case 2:
					settings.setTextSize(TextSize.NORMAL);

					break;

				case 3:
					settings.setTextSize(TextSize.SMALLER);

					break;

				case 4:
					settings.setTextSize(TextSize.SMALLEST);

					break;

				default:
					break;
				}
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}
}
