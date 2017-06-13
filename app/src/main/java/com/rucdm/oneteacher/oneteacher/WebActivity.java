package com.rucdm.oneteacher.oneteacher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rucdm.oneteacher.oneteacher.wxutil.Constants;
import com.rucdm.oneteacher.oneteacher.wxutil.Util;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WebActivity extends Activity implements View.OnClickListener {

    private IWXAPI api = WXAPIFactory
            .createWXAPI(this, Constants.APP_ID, false);
    protected static final int DISMISS = 0;
    private WebView wv_web_activity;
    private String URL = "";
    private String shareUrl = "";
    private String shareTitle = "";
    private ImageView iv_main_child_register_back;
    private LinearLayout ll_hide;
    private LinearLayout ll_loading_failed;
    private TextView tv_web_back, tv_web_fresh;
    private RelativeLayout rl_web_share;
    private ImageView iv_web_share;
    private TextView tv_web_share_cancel;
    private LinearLayout ll_web_share_friend;
    private LinearLayout ll_web_share_comment;
    private MyWebChromeClient mwcc;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case DISMISS:
                    ll_hide.setVisibility(View.GONE);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initLayout();
        onNewIntent(getIntent());
        initEvent();
    }

    private void initEvent() {
        mwcc = new MyWebChromeClient();
        wv_web_activity.requestFocus();
        wv_web_activity.getSettings().setCacheMode(
                WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_web_activity.getSettings().setJavaScriptCanOpenWindowsAutomatically(
                true);
        wv_web_activity.getSettings().setJavaScriptEnabled(true);
        wv_web_activity.getSettings().setSupportZoom(true);
        wv_web_activity.getSettings().setBuiltInZoomControls(true);
        wv_web_activity.getSettings().setUseWideViewPort(true);
        wv_web_activity.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wv_web_activity.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_web_activity.getSettings().setLoadWithOverviewMode(true);
        wv_web_activity.getSettings().setAppCacheEnabled(true);
        wv_web_activity.getSettings().setDomStorageEnabled(true);
        wv_web_activity.setWebViewClient(new MyWebViewClient());
        wv_web_activity.setWebChromeClient(mwcc);
        iv_main_child_register_back.setOnClickListener(this);

        tv_web_back.setOnClickListener(this);
        tv_web_fresh.setOnClickListener(this);
        rl_web_share.setOnClickListener(this);
        iv_web_share.setOnClickListener(this);
        tv_web_share_cancel.setOnClickListener(this);
        ll_web_share_friend.setOnClickListener(this);
        ll_web_share_comment.setOnClickListener(this);
        ll_hide.setOnClickListener(this);
    }

    private void initLayout() {
        wv_web_activity = (WebView) findViewById(R.id.wv_web_activity);
        iv_main_child_register_back = (ImageView) findViewById(R.id.iv_web_back);
        ll_hide = (LinearLayout) findViewById(R.id.ll_hide);
        ll_loading_failed = (LinearLayout) findViewById(R.id.ll_loading_failed);
        tv_web_back = (TextView) findViewById(R.id.tv_web_back);
        tv_web_fresh = (TextView) findViewById(R.id.tv_web_fresh);
        rl_web_share = (RelativeLayout) findViewById(R.id.rl_web_share);
        iv_web_share = (ImageView) findViewById(R.id.iv_web_share);
        tv_web_share_cancel = (TextView) findViewById(R.id.tv_web_share_cancel);
        ll_web_share_friend = (LinearLayout) findViewById(R.id.ll_web_share_friend);
        ll_web_share_comment = (LinearLayout) findViewById(R.id.ll_web_share_comment);
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Log.e("TAG", "正在获取进度");
            if (newProgress >= 100) {
                handler.sendEmptyMessageDelayed(DISMISS, 1500);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.e("TAG", "得到了新的title 为 :" + title);
            if (title == null || title.trim().equals("") || title.contains("capp.")) {
                iv_web_share.setVisibility(View.GONE);
            } else {
                iv_web_share.setVisibility(View.VISIBLE);
            }
            shareTitle = new String(title);
            // Toast.makeText(WebActivity.this, "得到的TITLE消息是 ： " + title, 0)
            // .show();
        }
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("TAG", "shouldOverrideUrl调用" + url);
            view.loadUrl(url);
            // 设置 缓存模式
            return true;
        }

        // 开始加载网页时要做的工作
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e("TAG", "正在加载网页" + url);
        }

        // 加载完成时要做的工作
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e("TAG", "加载完成" + url);
            shareUrl = url.replace("capp", "www");
            handler.sendEmptyMessageDelayed(DISMISS, 2500);
        }

        // 加载错误时要做的工作
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d("TAG", "加载失败error=" + description);
            ll_loading_failed.setVisibility(View.VISIBLE);
            // Toast.makeText(IndexActivity.this, errorCode + "/" + description,
            // Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String path = intent.getStringExtra("URL");
        URL = path;
        Log.e("TAG", "加载页面" + path);
        wv_web_activity.loadUrl(path);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_web_back:
                finish();
                break;

            case R.id.tv_web_back:
                finish();
                break;

            case R.id.tv_web_fresh:
                wv_web_activity.loadUrl(URL);
                ll_hide.setVisibility(View.VISIBLE);
                ll_loading_failed.setVisibility(View.GONE);
                break;

            case R.id.rl_web_share:

                break;

            case R.id.iv_web_share:
//                rl_web_share.setVisibility(View.VISIBLE);
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_SUBJECT, shareTitle);
                intent1.putExtra(Intent.EXTRA_TEXT, shareUrl);
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1, "share"));
                break;


            case R.id.tv_web_share_cancel:
                rl_web_share.setVisibility(View.GONE);
                break;

            case R.id.ll_web_share_friend:
                Toast.makeText(WebActivity.this, "正在分享给微信好友，请稍后....", Toast.LENGTH_SHORT).show();
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = shareUrl;
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = shareTitle;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon108);
                // msg.description = "这是何种描述呢";
                msg.thumbData = Util.bmpToByteArray(bitmap, true);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage"); //
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;
                // SendMessageToWX.Req.WXSceneSession
                api.sendReq(req);
                break;

            case R.id.ll_web_share_comment:
                Toast.makeText(WebActivity.this, "正在分享到朋友圈，请稍后....", Toast.LENGTH_SHORT).show();
                WXWebpageObject webpage1 = new WXWebpageObject();
                webpage1.webpageUrl = shareUrl;
                WXMediaMessage msg1 = new WXMediaMessage(webpage1);
                msg1.title = shareTitle;
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),
                        R.drawable.icon108);
                // msg1.description = "这是何种描述呢";
                msg1.thumbData = Util.bmpToByteArray(bitmap1, true);
                SendMessageToWX.Req req1 = new SendMessageToWX.Req();
                req1.transaction = buildTransaction("webpage"); //
                req1.message = msg1;
                req1.scene = SendMessageToWX.Req.WXSceneTimeline;
                // SendMessageToWX.Req.WXSceneSession
                api.sendReq(req1);
                break;

            case R.id.ll_hide:

                break;

            default:
                break;
        }
    }

    // 设置回退
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_web_activity.canGoBack()) {
            wv_web_activity.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }


}
