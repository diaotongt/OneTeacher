package com.rucdm.oneteacher.oneteacher.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.rucdm.oneteacher.oneteacher.R;
import com.rucdm.oneteacher.oneteacher.WebActivity;
import com.rucdm.oneteacher.oneteacher.utils.SpUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/8/16.
 */
public class IndexFragment extends Fragment implements View.OnClickListener {

    private final int mid = SpUtils.getInstance(getActivity()).getValue("MID", -1);
    private final String logId = SpUtils.getInstance(getActivity()).getValue("LOGID", "");
    private static Context context;
    private View view;
    private RadioGroup rg_me;
    private ImageView iv_paper;
    private ImageView iv_book;
    private ImageView iv_info;
    private ImageView iv_index_search;

    public IndexFragment() {

    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public IndexFragment(RadioGroup rg_main) {
        rg_me = rg_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(context, R.layout.fragment_index, null);
        initLayout();
        initEvent();
        initData();
        return view;
    }

    private void initData() {

    }

    private void initEvent() {
        iv_paper.setOnClickListener(this);
        iv_book.setOnClickListener(this);
        iv_info.setOnClickListener(this);
        iv_index_search.setOnClickListener(this);
    }

    private void initLayout() {
        iv_paper = (ImageView) view.findViewById(R.id.iv_paper);
        iv_book = (ImageView) view.findViewById(R.id.iv_book);
        iv_info = (ImageView) view.findViewById(R.id.iv_info);
        iv_index_search = (ImageView) view.findViewById(R.id.iv_index_search);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_paper:
                rg_me.check(R.id.rb_paper);
                break;

            case R.id.iv_book:
                rg_me.check(R.id.rb_book);
                break;

            case R.id.iv_info:
                rg_me.check(R.id.rb_info);
                break;

            case R.id.iv_index_search:
                String enCodeUrl = null;

                try {
                    enCodeUrl = URLEncoder.encode("/Search/SearchIndex", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String url = "http://capp.1xuezhe.exuezhe.com/Home/applogin?loginmid="
                        + mid + "&loginwxid=" + logId + "&rtnurl=" + enCodeUrl;
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
                break;

            default:

                break;
        }
    }
}
