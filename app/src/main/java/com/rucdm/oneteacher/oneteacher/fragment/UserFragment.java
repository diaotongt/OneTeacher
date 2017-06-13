package com.rucdm.oneteacher.oneteacher.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rucdm.oneteacher.oneteacher.R;
import com.rucdm.oneteacher.oneteacher.SettingActivity;
import com.rucdm.oneteacher.oneteacher.SettingChildActivity;
import com.rucdm.oneteacher.oneteacher.WebActivity;
import com.rucdm.oneteacher.oneteacher.utils.CacheUtils;
import com.rucdm.oneteacher.oneteacher.utils.SpUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/8/16.
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    protected static final int DISMISS = 0;
    private final int mid = SpUtils.getInstance(getActivity()).getValue("MID", -1);
    private final String logId = SpUtils.getInstance(getActivity()).getValue("LOGID", "");
    private String userUrl = "http://capp.1xuezhe.exuezhe.com/Home/applogin?loginmid="
            + mid + "&loginwxid=" + logId + "&rtnurl=/member/indexyjs";
    private TextView tv_setting;
    private static Context context;
    private View view;
    private WebView wv_paper;
    private LinearLayout ll_hide;
    private MyWebChromeClient mwcc;
    private boolean isFirst;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(context, R.layout.fragment_user, null);
        initLayout();
        initEvent();
        initData();
        wv_paper.loadUrl(userUrl);
        return view;
    }

    private void initData() {
        isFirst = true;
        mwcc = new MyWebChromeClient();
        wv_paper.requestFocus();
        wv_paper.getSettings().setCacheMode(
                WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_paper.getSettings().setJavaScriptCanOpenWindowsAutomatically(
                true);
        wv_paper.getSettings().setJavaScriptEnabled(true);
        wv_paper.getSettings().setSupportZoom(true);
        wv_paper.getSettings().setBuiltInZoomControls(true);
        wv_paper.getSettings().setUseWideViewPort(true);
        wv_paper.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wv_paper.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_paper.getSettings().setLoadWithOverviewMode(true);
        wv_paper.getSettings().setAppCacheEnabled(true);
        wv_paper.getSettings().setDomStorageEnabled(true);
        wv_paper.setWebViewClient(new MyWebViewClient());
        wv_paper.setWebChromeClient(mwcc);
        ll_hide.setOnClickListener(this);
    }

    private void initEvent() {
        tv_setting.setOnClickListener(this);
    }

    private void initLayout() {
        tv_setting = (TextView) view.findViewById(R.id.tv_setting);
        wv_paper = (WebView) view.findViewById(R.id.wv_paper);
        ll_hide = (LinearLayout) view.findViewById(R.id.ll_hide);
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
            // Toast.makeText(WebActivity.this, "得到的TITLE消息是 ： " + title, 0)
            // .show();
        }
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("TAG", "shouldOverrideUrl调用" + url);
            if (isFirst || url.equals(userUrl)) {
                isFirst = false;
                view.loadUrl(url);
            } else {
                isFirst = false;
                Intent in = new Intent(context, WebActivity.class);
                in.putExtra("URL", url);
                startActivity(in);
            }
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
            handler.sendEmptyMessageDelayed(DISMISS, 2500);
        }

        // 加载错误时要做的工作
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Log.d("TAG", "加载失败error=" + description);
            // Toast.makeText(IndexActivity.this, errorCode + "/" + description,
            // Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        Intent setIntent = new Intent(context,
                SettingChildActivity.class);
        Integer mid = SpUtils.getInstance(context).getValue("MID",
                -1);
        switch (v.getId()) {
            case R.id.tv_setting:
                context.startActivity(new Intent(context, SettingActivity.class));
                getActivity().finish();
                break;

            default:
                break;
        }
    }
}
