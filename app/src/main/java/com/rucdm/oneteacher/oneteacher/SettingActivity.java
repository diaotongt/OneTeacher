package com.rucdm.oneteacher.oneteacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rucdm.oneteacher.oneteacher.utils.CacheUtils;
import com.rucdm.oneteacher.oneteacher.utils.SpUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SettingActivity extends Activity implements View.OnClickListener {
    private static final String INDEXPOSITION = "INDEXPOSITION";
    private static final String TESTPOSITION = "TESTPOSITION";
    private static final String SETTINGCHILDPOSITION = "SETTINGCHILDPOSITION";
    private static final int CHANGEPWD = 60;
    private static final int CLEARCACHE = 61;
    private static final int USEHELP = 62;
    private static final int QUESTIONS = 63;
    private static final int ABOUTUS = 64;
    private static final int SETTING = 58;
    private static final int USERCENTER = 50;
    private ImageView iv_index_index_setting_back;
    private RelativeLayout rl_index_index_setting_changepwd;
    private RelativeLayout rl_index_index_setting_clearcache;
    private RelativeLayout rl_index_index_setting_usehelp;
    private RelativeLayout rl_index_index_setting_question;
    private RelativeLayout rl_index_index_setting_aboutus;
    private RelativeLayout rl_index_index_setting_advise;
    private TextView tv_index_setting_logout;
    private long mcacheSize = 0;
    private TextView tv_cache_size;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        initLayout();
        initEvent();
        initData();
    }

    private void initData() {
        String cacheSize = null;
        try {
            cacheSize = CacheUtils.getCacheSize(getExternalCacheDir());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        tv_cache_size.setText(cacheSize);
        File externalCacheDir = getExternalCacheDir();
        String path = externalCacheDir.getAbsolutePath();
        Log.e("TAG", path);
        long qq = externalCacheDir.length();
        try {
            FileInputStream fis = new FileInputStream(externalCacheDir);
            int fuck = fis.available();
            Log.e("TAG", fuck + "是文件的大小");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("TAG", "文件大小  " + qq);
        Message msg = Message.obtain();
        msg.what = 0;
        msg.obj = qq;
    }

    private void initEvent() {
        rl_index_index_setting_advise.setOnClickListener(this);
        rl_index_index_setting_clearcache.setOnClickListener(this);
        rl_index_index_setting_usehelp.setOnClickListener(this);
        rl_index_index_setting_question.setOnClickListener(this);
        rl_index_index_setting_aboutus.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_index_setting_logout.setOnClickListener(this);
    }

    private void initLayout() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rl_index_index_setting_advise = (RelativeLayout) findViewById(R.id.rl_index_index_setting_advise);
        rl_index_index_setting_clearcache = (RelativeLayout) findViewById(R.id.rl_index_index_setting_clearcaches);
        rl_index_index_setting_usehelp = (RelativeLayout) findViewById(R.id.rl_index_index_setting_usehelp);
        rl_index_index_setting_question = (RelativeLayout) findViewById(R.id.rl_index_index_setting_question);
        rl_index_index_setting_aboutus = (RelativeLayout) findViewById(R.id.rl_index_index_setting_aboutus);
        tv_index_setting_logout = (TextView) findViewById(R.id.tv_index_setting_logout);
        tv_cache_size = (TextView) findViewById(R.id.tv_cache_size);
    }

    @Override
    public void onClick(View v) {

        Intent setIntent = new Intent(SettingActivity.this,
                SettingChildActivity.class);
        Integer mid = SpUtils.getInstance(SettingActivity.this).getValue("MID",
                -1);
        switch (v.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                finish();
                break;

            case R.id.rl_index_index_setting_clearcaches:
                CacheUtils.cleanInternalCache(SettingActivity.this);
                String cacheSize = null;
                try {

                    cacheSize = CacheUtils.getCacheSize(getExternalCacheDir());
                } catch (Exception e1) {
                    Toast.makeText(SettingActivity.this, "出异常了", 0).show();
                    e1.printStackTrace();
                }
                tv_cache_size.setText(cacheSize);
                break;

            case R.id.rl_index_index_setting_usehelp:
                Intent in = new Intent(SettingActivity.this, WebActivity.class);
                String logId = SpUtils.getInstance(SettingActivity.this).getValue(
                        "LOGID", "");
                String path2 = "http://capp.1xuezhe.exuezhe.com/Home/applogin?loginmid="
                        + mid
                        + "&loginwxid="
                        + logId
                        + "&rtnurl=/help/help.html?r=1";
                in.putExtra("URL", path2);
                startActivity(in);
                break;

            case R.id.rl_index_index_setting_question:
                Intent inq = new Intent(SettingActivity.this, WebActivity.class);
                String logIdq = SpUtils.getInstance(SettingActivity.this).getValue(
                        "LOGID", "");
                String pathq = "http://capp.1xuezhe.exuezhe.com/Home/applogin?loginmid="
                        + mid
                        + "&loginwxid="
                        + logIdq
                        + "&rtnurl=/help/question.html?r=1";
                inq.putExtra("URL", pathq);
                startActivity(inq);
                break;

            case R.id.rl_index_index_setting_aboutus:
                setIntent.putExtra(SETTINGCHILDPOSITION, ABOUTUS);
                startActivity(setIntent);
                break;
            case R.id.tv_index_setting_logout:
                SpUtils.getInstance(SettingActivity.this).save("ISLOG", false);
                startActivity(new Intent(SettingActivity.this, WelcomeActivity.class));
                finish();
                break;

            case R.id.rl_index_index_setting_advise:
                int mid1 = SpUtils.getInstance(SettingActivity.this).getValue(
                        "MID", -1);
                String logId1 = SpUtils.getInstance(SettingActivity.this).getValue(
                        "LOGID", "");
                String enCodeUrl1 = null;
                try {
                    enCodeUrl1 = URLEncoder.encode("/help/contact.html?r=1",
                            "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String path1 = "http://capp.1xuezhe.exuezhe.com/Home/applogin?loginmid="
                        + mid1 + "&loginwxid=" + logId1 + "&rtnurl=" + enCodeUrl1;
                Intent intent1 = new Intent(SettingActivity.this, WebActivity.class);
                intent1.putExtra("URL", path1);
                startActivity(intent1);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(SettingActivity.this, MainActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
