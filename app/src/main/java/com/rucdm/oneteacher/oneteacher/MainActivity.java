package com.rucdm.oneteacher.oneteacher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rucdm.oneteacher.oneteacher.fragment.BookFragment;
import com.rucdm.oneteacher.oneteacher.fragment.IndexFragment;
import com.rucdm.oneteacher.oneteacher.fragment.InfoFragment;
import com.rucdm.oneteacher.oneteacher.fragment.PaperFragment;
import com.rucdm.oneteacher.oneteacher.fragment.UserFragment;

public class MainActivity extends FragmentActivity {
    private static final int BACK_REFRESH = 1;
    private FrameLayout fl_main;
    private RadioGroup rg_main;
    private IndexFragment iFragment;
    private InfoFragment inFragment;
    private BookFragment bFragment;
    private PaperFragment pFragment;
    private UserFragment uFragment;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case BACK_REFRESH:
                    isExit = false;
                    break;



                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initlayout();
        initEvent();
        initData();
        rg_main.check(R.id.rb_index);
    }

    private void initData() {

    }

    private void initEvent() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction beginTransaction = getSupportFragmentManager()
                        .beginTransaction();

                hideFragment(beginTransaction);
                switch (checkedId) {
                    case R.id.rb_index:
                        if (iFragment == null) {
                            iFragment = new IndexFragment(rg_main);
                            beginTransaction.add(R.id.fl_main, iFragment);
                        } else {
                            beginTransaction.show(iFragment);
                            // beginTransaction.remove(indexFragment);
                            // indexFragment = new IndexFragment(rg_index);
                            // beginTransaction.add(R.id.fl_index, indexFragment);
                        }
                        break;

                    case R.id.rb_info:
                        if (inFragment == null) {
                            inFragment = new InfoFragment();
                            beginTransaction.add(R.id.fl_main, inFragment);
                        } else {
                            beginTransaction.show(inFragment);
                            // beginTransaction.remove(readFragment);
                            // readFragment = new BookFragment();
                            // beginTransaction.add(R.id.fl_index, readFragment);
                        }
                        break;

                    case R.id.rb_book:
                        if (bFragment == null) {
                            bFragment = new BookFragment();
                            beginTransaction.add(R.id.fl_main, bFragment);
                        } else {
                            beginTransaction.show(bFragment);
                            // beginTransaction.remove(readFragment);
                            // readFragment = new BookFragment();
                            // beginTransaction.add(R.id.fl_index, readFragment);
                        }
                        break;

                    case R.id.rb_paper:
                        if (pFragment == null) {
                            pFragment = new PaperFragment();
                            beginTransaction.add(R.id.fl_main, pFragment);
                        } else {
                            beginTransaction.show(pFragment);
                            // beginTransaction.remove(readFragment);
                            // readFragment = new BookFragment();
                            // beginTransaction.add(R.id.fl_index, readFragment);
                        }
                        break;

                    case R.id.rb_user:
                        if (uFragment == null) {
                            uFragment = new UserFragment();
                            beginTransaction.add(R.id.fl_main, uFragment);
                        } else {
                            beginTransaction.show(uFragment);
                            // beginTransaction.remove(readFragment);
                            // readFragment = new BookFragment();
                            // beginTransaction.add(R.id.fl_index, readFragment);
                        }
                        break;

                }
                beginTransaction.commit();
            }
        });

    }

    private void initlayout() {
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
    }

    public void hideFragment(FragmentTransaction beginTransaction) {
        if (iFragment != null) {
            beginTransaction.hide(iFragment);
        }
        if (inFragment != null) {
            beginTransaction.hide(inFragment);
        }
        if (bFragment != null) {
            beginTransaction.hide(bFragment);
        }
        if (pFragment != null) {
            beginTransaction.hide(pFragment);
        }
        if (uFragment != null) {
            beginTransaction.hide(uFragment);
        }

    }

    private boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, "再点一次退出", 0).show();
                handler.sendEmptyMessageDelayed(BACK_REFRESH, 2000);
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        handler.removeCallbacksAndMessages(null);
        return super.onKeyDown(keyCode, event);
    }
}
