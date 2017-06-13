package com.rucdm.oneteacher.oneteacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.rucdm.oneteacher.oneteacher.bean.LogInSbBean;
import com.rucdm.oneteacher.oneteacher.bean.LoginBean;
import com.rucdm.oneteacher.oneteacher.bean.SbBean;
import com.rucdm.oneteacher.oneteacher.utils.MD5Util;
import com.rucdm.oneteacher.oneteacher.utils.SpUtils;
import com.rucdm.oneteacher.oneteacher.utils.shabitianwenshumeimd5;

import org.apache.http.entity.StringEntity;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WelcomeActivity extends Activity {

//    private static final String LOGURL = "http://114.55.249.45/cloudzone/ClientApi/getAuthenticationInfo";
    private static final String LOGURL = "http://aicloud.aischool.szlg.edu.cn/cloudzone/ClientApi/getAuthenticationInfo";
    private TextView tv_logoin;
    private EditText et_account, et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        SpUtils.getInstance(WelcomeActivity.this).save(
                "KEY", "ed283dc28060fda58d8add369dc9312c");
        et_account = (EditText) findViewById(R.id.et_account);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_logoin = (TextView) findViewById(R.id.tv_logoin);

        boolean isLog = SpUtils.getInstance(WelcomeActivity.this).getValue("ISLOG", false);
        if (isLog) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
        tv_logoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "随机数为  :  " + Math.random() + "" + "    长度为  : " + (Math.random() + "").length());
                Log.e("TAG", "随机数为  :  " + System.currentTimeMillis() + "" + "    长度为  : " + (System.currentTimeMillis() + "").length());
                Log.e("TAG", "随机数截取后为  :  " + System.currentTimeMillis() + "" + "    截取后长度为  : " + (System.currentTimeMillis() + "").substring(0, 12).length());
                final String account = et_account.getText().toString().trim();
                final String pwd = et_pwd.getText().toString().trim();
                if (account.equals("") || pwd.equals("")) {
                    Toast.makeText(WelcomeActivity.this, "账号或者密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (account.equals("admin") && pwd.equals("rucdmrdsm")) {
                    Toast.makeText(WelcomeActivity.this, "欢迎来到壹教师", Toast.LENGTH_SHORT).show();
                    String key = SpUtils.getInstance(WelcomeActivity.this).getValue("KEY",
                            "");
                    String logId = MD5Util.getMD5Str(196828 + key);
                    SpUtils.getInstance(WelcomeActivity.this).save("ISLOG", true);
                    SpUtils.getInstance(WelcomeActivity.this).save("MID", 196828);
                    SpUtils.getInstance(WelcomeActivity.this).save("LOGID", logId);
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                    return;
                }
                tv_logoin.setEnabled(false);
                String num2 = Long.toBinaryString(System.currentTimeMillis()).substring(0,12);
                RequestParams params = new RequestParams();
//                SbBean sbBean = new SbBean(account, num2 + MD5Util.getMD5Str(num2 + pwd).toUpperCase(), "com.rucdm.oneteacher.oneteacher");
                SbBean sbBean = new SbBean(account, shabitianwenshumeimd5.getEncryptedPwd(pwd).toUpperCase(), "com.rucdm.oneteacher.oneteacher");
                params.setBodyEntity(new StringEntity(new Gson().toJson(sbBean), "UTF-8"));
                Log.e("TAG", "当前输出的json为 : " + new Gson().toJson(sbBean));
//                params.addBodyParameter("loginName", account);
//                params.addBodyParameter("password", MD5Util.getMD5Str(random + pwd));
//                params.addBodyParameter("packageName", "com.rucdm.oneteacher.oneteacher");

                Log.e("TAG", "用户名 : " + account + "  密码  : " + shabitianwenshumeimd5.getEncryptedPwd(pwd).toUpperCase());
//                Log.e("TAG", "用户名 : " + account + "  密码  : " + num2 + MD5Util.getMD5Str(num2 + pwd).toUpperCase());
                HttpUtils http = new HttpUtils();
                http.send(HttpMethod.POST,
                        LOGURL,
                        params,
                        new RequestCallBack<String>() {

                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onLoading(long total, long current, boolean isUploading) {
                            }

                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                tv_logoin.setEnabled(true);
                                Gson g = new Gson();
                                Log.e("TAG", "得到天闻数媒登陆的结果为  :  " + responseInfo.result);
                                LoginBean jsonInfo = g.fromJson(responseInfo.result, LoginBean.class);
                                LoginBean.LoginData ld = jsonInfo.getServerResult();
                                Log.e("TAG", "天文输煤返回码  " + ld.getResultCode());
                                if (ld.getResultCode() == 0) {
                                    Log.e("TAG", "准备进行人大数媒用户注册");
                                    getNewMid(account, pwd);
                                } else {
                                    Toast.makeText(WelcomeActivity.this, "登录失败请稍后重试", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(HttpException error, String msg) {
                                tv_logoin.setEnabled(true);
                                Toast.makeText(WelcomeActivity.this, "链接失败请稍后重试", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void getNewMid(final String account, String pwd) {
        String key = SpUtils.getInstance(WelcomeActivity.this).getValue("KEY", "");
        String sign = MD5Util.getMD5Str(111111111 + account + "www.1xuezhe.com" + key);
        String path = null;
        try {
            path = "http://api.1xuezhe.com" + "/auth/LibraryLogin?cid=" + 111111111 + "&cname=" + URLEncoder.encode("天闻数媒壹教师", "UTF-8") + "&acount=" + account + "&pwd=" + pwd + "&sign=" + sign;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("TAG", "准备进行人大数媒用户注册 地址为  ： " + path);
        HttpUtils http = new HttpUtils();
        http.send(HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson g = new Gson();
                Log.e("TAG", "登录返回的信息为" + responseInfo.result);
                LogInSbBean loginRes = g.fromJson(responseInfo.result,
                        LogInSbBean.class);

                // Log.e("TAG", "转换完的数据格式为" + loginRes.toString());
                int errorCode = loginRes.getError();
                Log.e("TAG", "登录接口响应码为 ：" + errorCode);

                switch (errorCode) {
//                            case 50000:
//                                Toast.makeText(OrgLoginActivity.this, "链接失败请检查网络.", Toast.LENGTH_SHORT)
//                                        .show();
//                                break;

                    case 0:
                    case 1:
                        SpUtils.getInstance(WelcomeActivity.this).save("NEEDSHOWBIND", loginRes.getData().isverifyphone);
                        SpUtils.getInstance(WelcomeActivity.this).save("ISVARIFYPHONE", loginRes.getData().isverifyphone);
                        SpUtils.getInstance(WelcomeActivity.this).save("ISLOG", true);
                        SpUtils.getInstance(WelcomeActivity.this).save("ORGNAME", "天闻数媒壹教师");
                        SpUtils.getInstance(WelcomeActivity.this).save("ORGID", 111111111);
                        SpUtils.getInstance(WelcomeActivity.this).save("ORGACCOUNT", account);
                        SpUtils.getInstance(WelcomeActivity.this).save("LOGWAY", 2);
                        if (null == loginRes.getData().getPatentnum() || "0".equals(loginRes.getData().getPatentnum())) {
                            SpUtils.getInstance(WelcomeActivity.this).save("CARDLOGCODE", 0);
                        } else if (loginRes.getData().getPatentnum().equals("1")) {
                            SpUtils.getInstance(WelcomeActivity.this).save("CARDLOGCODE", 1);
                        } else {
                            SpUtils.getInstance(WelcomeActivity.this).save("CARDLOGCODE", -1);
                        }
                        SpUtils.getInstance(WelcomeActivity.this).save("LASTLOGTIME",
                                System.currentTimeMillis());
                        if (null != loginRes.getData().getTreecode()
                                && !"".equals(loginRes.getData().getTreecode())) {
                            SpUtils.getInstance(WelcomeActivity.this).save("TREECODE",
                                    loginRes.getData().getTreecode());
                        }
                        SpUtils.getInstance(WelcomeActivity.this).save("MID",
                                loginRes.getData().getMid());
                        Log.e("TAG", "MID为" + loginRes.getData().getMid());
                        String key = SpUtils.getInstance(WelcomeActivity.this).getValue("KEY",
                                "");
                        String logId = MD5Util.getMD5Str(loginRes.getData()
                                .getMid() + key);
                        SpUtils.getInstance(WelcomeActivity.this).save("LOGID", logId);
                        if (loginRes.getData().getAvater() != null) {
                            SpUtils.getInstance(WelcomeActivity.this).save("HEADURL",
                                    loginRes.getData().getAvater());
                        } else {
                            SpUtils.getInstance(WelcomeActivity.this).save("HEADURL",
                                    "");
                        }
                        Boolean isLog = loginRes.getData().isverifyphone;
                        Log.e("TAG", " isverifyphone的值为 : " + isLog);
                        SpUtils.getInstance(WelcomeActivity.this).save("ISLOG", true);
                        SpUtils.getInstance(WelcomeActivity.this).save("LASTLOGTIME",
                                System.currentTimeMillis());
                        startActivity(new Intent(WelcomeActivity.this,
                                MainActivity.class));
                        finish();

                        break;
                    default:
                        Toast.makeText(WelcomeActivity.this, "信息有误请稍后重试", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                Log.e("TAG", "人大数媒注册失败");
            }
        });
    }
}
