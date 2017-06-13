package com.rucdm.oneteacher.oneteacher.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.rucdm.oneteacher.oneteacher.R;
import com.rucdm.oneteacher.oneteacher.WebActivity;
import com.rucdm.oneteacher.oneteacher.bean.CommunityInfoBean;
import com.rucdm.oneteacher.oneteacher.utils.SpUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {

    private final int mid = SpUtils.getInstance(getActivity()).getValue("MID", -1);
    private final String logId = SpUtils.getInstance(getActivity()).getValue("LOGID", "");
    private String treecode = "03";
    private final String COMMUNITYINFOURL = "http://api.1xuezhe.com/news/byclass?classcode=01&page=1&rtn=15";
    private static Context context;
    private View view;
    private PullToRefreshListView mlv_index_community_info_content;
    private MyListAdapter mlAdapter;
    private ImageView iv_index_community_info_subjec_arrow_down;
    private LinearLayout ll_index_community_info_subject;
    private GridView gv_community_info_subject;
    private MySubjectAdapter msAdapter;
    private int subjectSign = Integer.parseInt(treecode) - 1;
    private int code = subjectSign + 1;
    private int page = 1;
    private List<String> subjectContent;
    private List<CommunityInfoBean.CommunityInfoData> communityInfoList;
    private LinearLayout ll_index_community_info_loading;
    private ImageView iv_index_community_info_search;
    private TextView tv_index_community_info_department;
    protected static final int LVRESET = 0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case LVRESET:
                    mlv_index_community_info_content.onRefreshComplete();
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
        view = View.inflate(context, R.layout.fragment_info, null);
        initLayout();
        initEventThing();
        initData();
        return view;
    }


    protected void loadmore(String path) {
        HttpUtils http = new HttpUtils(2000);
        http.send(HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson g = new Gson();
                CommunityInfoBean jsonObj = g.fromJson(responseInfo.result,
                        CommunityInfoBean.class);
                communityInfoList.addAll(jsonObj.getData());
                Log.e("TAG", communityInfoList.size() + "");
                mlAdapter.notifyDataSetChanged();
                ll_index_community_info_loading.setVisibility(View.GONE);
                handler.sendEmptyMessage(LVRESET);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ll_index_community_info_loading.setVisibility(View.GONE);
                handler.sendEmptyMessage(LVRESET);
            }

            @Override
            public void onStart() {
            }

        });
    }

    protected void refresh(final String path) {
        HttpUtils http = new HttpUtils(2000);
        http.send(HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // Log.e("TAG", responseInfo.result);
                SpUtils.getInstance(context).save(path, responseInfo.result);
                Gson g = new Gson();
                CommunityInfoBean jsonBean = g.fromJson(responseInfo.result,
                        CommunityInfoBean.class);
                communityInfoList = new ArrayList<CommunityInfoBean.CommunityInfoData>();
                communityInfoList.addAll(jsonBean.getData());
                mlv_index_community_info_content.setAdapter(mlAdapter);
                ll_index_community_info_loading.setVisibility(View.GONE);
                handler.sendEmptyMessage(LVRESET);
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                String result = SpUtils.getInstance(context).getValue(path, "");
                if (!result.equals("")) {
                    Gson g = new Gson();
                    CommunityInfoBean jsonBean = g.fromJson(result,
                            CommunityInfoBean.class);
                    communityInfoList = new ArrayList<CommunityInfoBean.CommunityInfoData>();
                    communityInfoList.addAll(jsonBean.getData());
                    mlv_index_community_info_content.setAdapter(mlAdapter);
                }
                ll_index_community_info_loading.setVisibility(View.GONE);
                handler.sendEmptyMessage(LVRESET);
            }
        });
    }

    private void initData() {
        mlAdapter = new MyListAdapter();
        msAdapter = new MySubjectAdapter();
        subjectContent = new ArrayList<String>();
        subjectContent.add("法律");
        subjectContent.add("经管");
        subjectContent.add("教育");
        subjectContent.add("历史");
        subjectContent.add("文学与艺术");
        subjectContent.add("文化与传播");
        subjectContent.add("哲学");
        subjectContent.add("政治与社会");
        subjectContent.add("其他");
        getDataFromNet(COMMUNITYINFOURL);


        mlv_index_community_info_content.setMode(PullToRefreshBase.Mode.BOTH);
        mlv_index_community_info_content.scrollTo(0, 0);
        mlv_index_community_info_content
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        String path = "http://api.1xuezhe.com/news/byclass?classcode="
                                + code + "&page=1&rtn=15";
                        refresh(path);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        page++;
                        String path = "http://api.1xuezhe.com/news/byclass?classcode="
                                + code + "&page=" + page + "&rtn=15";
                        loadmore(path);
                    }
                });

        mlv_index_community_info_content
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String nid = communityInfoList.get(position - 1)
                                .getNid() + "";

                        String code = communityInfoList.get(position - 1)
                                .getClasscode();
                        String enCodeUrl = null;
                        try {
                            enCodeUrl = URLEncoder.encode(
                                    "/Academic/detail?nid=" + nid + "&code="
                                            + code + "&tagtype=1&r=1", "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String url = "http://capp.1xuezhe.exuezhe.com/Home/applogin?loginmid="
                                + mid
                                + "&loginwxid="
                                + logId
                                + "&rtnurl="
                                + enCodeUrl;
//                        Intent intent = new Intent(context,
//                                CommunityChildActivity.class);
//                        intent.putExtra("COMMUNITYPOSITION", 31);
//                        intent.putExtra("NID", nid);
//                        intent.putExtra("CODE", code);
//                        context.startActivity(intent);
                        Intent intent = new Intent(context,
                                WebActivity.class);
                        intent.putExtra("URL", url);
                        startActivity(intent);
                    }
                });
    }

    private void initEventThing() {
        ll_index_community_info_subject.setOnClickListener(this);
        iv_index_community_info_search.setOnClickListener(this);
    }

    private void initLayout() {
        mlv_index_community_info_content = (PullToRefreshListView) view
                .findViewById(R.id.mlv_index_community_info_content);
        mlv_index_community_info_content.setFocusable(false);
        ll_index_community_info_subject = (LinearLayout) view
                .findViewById(R.id.ll_index_community_info_subject);
        iv_index_community_info_subjec_arrow_down = (ImageView) view
                .findViewById(R.id.iv_index_community_info_subjec_arrow_down);
        ll_index_community_info_loading = (LinearLayout) view
                .findViewById(R.id.ll_index_community_info_loading);
        iv_index_community_info_search = (ImageView) view
                .findViewById(R.id.iv_index_community_info_search);
        tv_index_community_info_department = (TextView) view
                .findViewById(R.id.tv_index_community_info_department);
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return communityInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return communityInfoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            int type = 0;
            if (communityInfoList.get(position).getPicture().equals("")) {
                type = 0;
            } else {
                type = 1;
            }
            return type;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            mViewHolder mHolder = new mViewHolder();
            mSecViewHolder msHolder = new mSecViewHolder();
            CommunityInfoBean.CommunityInfoData infoBean = communityInfoList.get(position);
            int type = getItemViewType(position);
            if (convertView == null) {
                switch (type) {
                    case 0:
                        convertView = View.inflate(context,
                                R.layout.item_community_info, null);
                        mHolder.tvTitle = (TextView) convertView
                                .findViewById(R.id.tv_community_info_first_title);
                        mHolder.tvSign = (TextView) convertView
                                .findViewById(R.id.tv_community_info_first_sign);
                        mHolder.tvTime = (TextView) convertView
                                .findViewById(R.id.tv_community_info_first_time);
                        convertView.setTag(mHolder);
                        break;

                    case 1:
                        convertView = View.inflate(context,
                                R.layout.item_community_info_second, null);
                        msHolder.ivIcon = (ImageView) convertView
                                .findViewById(R.id.iv_community_info_second_icon);
                        msHolder.tvSign = (TextView) convertView
                                .findViewById(R.id.tv_community_info_second_sign);
                        msHolder.tvTime = (TextView) convertView
                                .findViewById(R.id.tv_community_info_second_time);
                        msHolder.tvTitle = (TextView) convertView
                                .findViewById(R.id.tv_community_info_second_title);
                        msHolder.tvSource = (TextView) convertView
                                .findViewById(R.id.tv_community_info_second_source);
                        convertView.setTag(msHolder);
                        break;

                    default:
                        break;
                }

            } else {
                switch (type) {
                    case 0:
                        mHolder = (mViewHolder) convertView.getTag();
                        break;

                    case 1:
                        msHolder = (mSecViewHolder) convertView.getTag();
                        break;

                    default:
                        break;
                }
            }
            switch (type) {
                case 0:
                    CommunityInfoBean.CommunityInfoData infoBean1 = communityInfoList.get(position);
                    mHolder.tvTitle.setText(infoBean1.getTitle());
                    mHolder.tvTime.setText(infoBean1.getAddtime().substring(5, 10));
                    switch (infoBean1.getType()) {
                        case 1:
                            mHolder.tvSign.setText("#课题#");

                            break;

                        case 2:
                            mHolder.tvSign.setText("#会议#");
                            break;

                        case 3:
                            mHolder.tvSign.setText("#政策#");
                            break;

                        case 11:
                            mHolder.tvSign.setText("#热点#");
                            break;

                        case 12:
                            mHolder.tvSign.setText("#观点#");
                            break;

                        case 13:
                            mHolder.tvSign.setText("#机构#");
                            break;

                        case 6:
                            mHolder.tvSign.setText("#书讯#");
                            break;

                        case 8:
                            mHolder.tvSign.setText("#成果#");
                            break;

                        case 1000:
                            mHolder.tvSign.setText("#其他#");
                            break;

                        default:
                            mHolder.tvSign.setText("#其他#");
                            break;
                    }
                    break;

                case 1:
                    CommunityInfoBean.CommunityInfoData infoBean2 = communityInfoList.get(position);
                    String time = infoBean2.getPublishdate().substring(5, 10);

                    msHolder.tvTime.setText(time);
                    msHolder.tvTitle.setText(infoBean2.getTitle());
                    msHolder.tvSource.setText(infoBean2.getDiscription() + "...");
                    switch (infoBean2.getType()) {
                        case 1:
                            msHolder.tvSign.setText("#项目#");

                            break;

                        case 2:
                            msHolder.tvSign.setText("#会议#");
                            break;

                        case 3:
                            msHolder.tvSign.setText("#政策#");
                            break;

                        case 4:
                            msHolder.tvSign.setText("#讣告#");
                            break;

                        case 5:
                            msHolder.tvSign.setText("#人事#");
                            break;

                        case 6:
                            msHolder.tvSign.setText("#新书#");
                            break;

                        case 7:
                            msHolder.tvSign.setText("#访问#");
                            break;

                        case 8:
                            msHolder.tvSign.setText("#成果#");
                            break;
                        default:
                            msHolder.tvSign.setText("#观点#");
                            break;
                    }
                    BitmapUtils btu = new BitmapUtils(context);
                    btu.display(msHolder.ivIcon, infoBean2.getPicture());

                default:
                    break;
            }
            return convertView;
        }
    }

    static class mViewHolder {
        TextView tvTitle;
        TextView tvContent;
        TextView tvNum;
        TextView tvAuthor;
        TextView tvTime;
        ImageView ivElite;
        TextView tvSign;
    }

    static class mSecViewHolder {
        TextView tvTitle;
        TextView tvSign;
        TextView tvSource;
        TextView tvNum;
        TextView tvAuthor;
        TextView tvTime;
        ImageView ivIcon;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_index_community_info_subject:
                final AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .create();
                alertDialog.show();
                alertDialog
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                iv_index_community_info_subjec_arrow_down
                                        .setImageResource(R.drawable.image_down);
                            }
                        });
                final int i = 0;
                Window window = alertDialog.getWindow();

                WindowManager.LayoutParams lp = window.getAttributes();
                lp.x = 10;
                lp.y = 350;
                // window.setAttributes(lp);
                window.setContentView(R.layout.item_index_book_subject);

                gv_community_info_subject = (GridView) window
                        .findViewById(R.id.gv_index_book_subject);
                gv_community_info_subject.setVerticalSpacing(10);
                gv_community_info_subject.setHorizontalSpacing(10);
                gv_community_info_subject.setAdapter(msAdapter);
                gv_community_info_subject
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent,
                                                    View view, int position, long id) {
                                Log.e("TAG", "成功监听");
                                if (subjectSign != position) {
                                    ll_index_community_info_loading
                                            .setVisibility(View.VISIBLE);
                                    subjectSign = position;
                                    code = subjectSign + 1;
                                    msAdapter.notifyDataSetInvalidated();
                                    Log.e("TAG", "成功调用");
                                    String path = "http://api.1xuezhe.com/news/byclass?classcode="
                                            + code + "&page=1&rtn=15";
                                    getDataFromNet(path);
                                    tv_index_community_info_department
                                            .setText(subjectContent
                                                    .get(subjectSign));
                                }
                                updateSubjectStatement(subjectSign);
                                alertDialog.dismiss();
                            }
                        });
                break;

            case R.id.iv_index_community_info_search:
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

    protected void getDataFromNet(final String path) {
        Log.e("TAG", "资讯页面正在访问的地址是   :" + path);
        HttpUtils http = new HttpUtils(2000);
        http.send(HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("TAG", "资讯页面得到的结果是  :" + responseInfo.result);
                SpUtils.getInstance(context).save(path, responseInfo.result);
                Gson g = new Gson();
                CommunityInfoBean jsonBean = g.fromJson(responseInfo.result,
                        CommunityInfoBean.class);
                communityInfoList = new ArrayList<CommunityInfoBean.CommunityInfoData>();
                communityInfoList.addAll(jsonBean.getData());
                mlv_index_community_info_content.setAdapter(mlAdapter);
                ll_index_community_info_loading.setVisibility(View.GONE);
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {

                String result = SpUtils.getInstance(context).getValue(path, "");
                if (!result.equals("")) {
                    Gson g = new Gson();
                    CommunityInfoBean jsonBean = g.fromJson(result,
                            CommunityInfoBean.class);
                    communityInfoList = new ArrayList<CommunityInfoBean.CommunityInfoData>();
                    communityInfoList.addAll(jsonBean.getData());
                    mlv_index_community_info_content.setAdapter(mlAdapter);
                }
                ll_index_community_info_loading.setVisibility(View.GONE);

            }
        });
    }

    protected void updateSubjectStatement(int subjectSign2) {

    }

    class MySubjectAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return subjectContent.size();
        }

        @Override
        public Object getItem(int position) {
            return subjectContent.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            mSubjectViewHolder msHolder = new mSubjectViewHolder();
            if (convertView == null) {
                convertView = View.inflate(context,
                        R.layout.item_index_book_subject_mode, null);
                msHolder.tvContent = (TextView) convertView
                        .findViewById(R.id.tv_subject_content);
                msHolder.ivContent = (ImageView) convertView
                        .findViewById(R.id.iv_subject_content);
                msHolder.llContent = (LinearLayout) convertView
                        .findViewById(R.id.ll_subject_content);
                convertView.setTag(msHolder);
            } else {
                msHolder = (mSubjectViewHolder) convertView.getTag();
            }
            String result = subjectContent.get(position);
            msHolder.tvContent.setText(result);
            // confirmTheChoose(subjectSign);
            Log.e("TAG", "正在设置颜色");
            if (position == subjectSign) {
                Log.e("TAG", "已经选择");
                msHolder.tvContent.setTextColor(Color.parseColor("#ff7a00"));
                msHolder.ivContent.setVisibility(View.VISIBLE);
            } else {
                msHolder.ivContent.setVisibility(View.INVISIBLE);
                msHolder.tvContent.setTextColor(Color.parseColor("#000000"));
            }
            // if (position == 0) {
            // msHolder.llContent
            // .setBackgroundResource(R.drawable.image_four_lines);
            // } else if (position == 1) {
            // msHolder.llContent
            // .setBackgroundResource(R.drawable.image_four_lines1);
            // } else if (position % 2 == 0) {
            // msHolder.llContent
            // .setBackgroundResource(R.drawable.image_four_lines2);
            // } else if (position % 2 == 1) {
            // msHolder.llContent
            // .setBackgroundResource(R.drawable.image_four_lines3);
            // }
            return convertView;
        }
    }

    static class mSubjectViewHolder {
        LinearLayout llContent;
        TextView tvContent;
        ImageView ivContent;
    }


}
