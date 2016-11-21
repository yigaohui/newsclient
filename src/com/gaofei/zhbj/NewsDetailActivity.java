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
		mTitle.setText("����");
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
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		oks.setTheme(OnekeyShareTheme.CLASSIC);
		
		// ����ʱNotification��ͼ������� 2.5.9�Ժ�İ汾�����ô˷���
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle("����");
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		oks.setTitleUrl("http://sharesdk.cn");
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		oks.setText("���Ƿ����ı�");
		// ��������ͼƬ������΢����������ͼƬ��Ҫͨ����˺�����߼�д��ӿڣ�������ע�͵���������΢��
		//oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		oks.setImagePath("/sdcard/text.jpg");//ȷ��SDcard������ڴ���ͼƬ
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		oks.setUrl("http://sharesdk.cn");
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		oks.setComment("���ǲ��������ı�");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		oks.setSite("ShareSDK");
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		oks.setSiteUrl("http://sharesdk.cn");

		// ��������GUI
		oks.show(this);
	}

	private int currentTextsize = 2;
	private String[] items = new String[] { "���������", "�������", "��������", "С������",
			"��С������" };
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
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

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
		builder.setNegativeButton("ȡ��", null);
		builder.show();
	}
}
