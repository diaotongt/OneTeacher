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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.rucdm.oneteacher.oneteacher.R;
import com.rucdm.oneteacher.oneteacher.WebActivity;
import com.rucdm.oneteacher.oneteacher.bean.BookCityBean;
import com.rucdm.oneteacher.oneteacher.utils.SpUtils;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class BookFragment extends Fragment implements View.OnClickListener {
    protected static final int LVRESET = 0;
    private final int mid = SpUtils.getInstance(getActivity()).getValue("MID", -1);
    private final String logId = SpUtils.getInstance(getActivity()).getValue("LOGID", "");
    private String treecode = "00";
    private final String BOOKCITYURL = "http://api.1xuezhe.com/book/ByClass?mid="
            + mid + "&page=1&cid=20&rtn=15&code=" + treecode + "&sort=0";
    private int subjectSign = Integer.parseInt(treecode) - 1;
    private int code = subjectSign + 1;
    private int page = 1;
    private static Context context;
    private View view;
    private LinearLayout ll_index_community_book_subject;
    private LinearLayout ll_index_book_city_loading;
    private PullToRefreshGridView pgv_book;
    private ImageView iv_index_community_book_search;
    private ImageView iv_index_community_book_subjec_arrow_down;
    private GridView gv_index_book_subject;
    private TextView tv_index_community_book_department;
    private List<String> subjectContent;
    private MySubjectAdapter msAdapter;
    private List<BookCityBean.BookCityData> bookCityList;
    private MyGridAdapter mGridAdapter;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case LVRESET:
                    pgv_book.onRefreshComplete();
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
        view = View.inflate(context, R.layout.fragment_book, null);
        initLayout();
        initEvent();
        initData();
        return view;
    }

    private void initData() {
        mGridAdapter = new MyGridAdapter();
        bookCityList = new ArrayList<BookCityBean.BookCityData>();
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
        msAdapter = new MySubjectAdapter();
        getDataFromNet(BOOKCITYURL);
        pgv_book.scrollTo(0, 0);
        pgv_book.setMode(PullToRefreshBase.Mode.BOTH);
        pgv_book
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<GridView> refreshView) {
                        page = 1;
                        refreshDataFromNet(BOOKCITYURL);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<GridView> refreshView) {
                        Log.e("TAG", "加载更多");
                        page++;
                        String URL = "http://api.1xuezhe.com/book/ByClass?mid="
                                + mid + "&page=" + page + "&cid=20&rtn=15&code=" + treecode + "&sort=0";
                        refresh(URL);
                    }
                });
        pgv_book
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        Log.e("TAG", "是否调用了onitemclick");
                        int book = bookCityList.get(position).getBookid();
                        int mid = SpUtils.getInstance(context).getValue("MID",
                                -1);
                        String logId = SpUtils.getInstance(context).getValue(
                                "LOGID", "");
                        String enCodeUrl = null;
                        try {
                            enCodeUrl = URLEncoder.encode(book + "&r=1",
                                    "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String path = "http://capp.1xuezhe.exuezhe.com/Home/applogin?loginmid="
                                + mid
                                + "&loginwxid="
                                + logId
                                + "&rtnurl=/book/BookInfo?bookId=" + enCodeUrl;
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("URL", path);
                        startActivity(intent);
                    }
                });
    }

    private void refresh(final String url) {

        HttpUtils http = new HttpUtils();
        http.send(HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson g = new Gson();
                BookCityBean jsonObj = g.fromJson(responseInfo.result,
                        BookCityBean.class);
                bookCityList.addAll(jsonObj.getData());
                Log.e("TAG", bookCityList.size() + "");
                mGridAdapter.notifyDataSetChanged();
                ll_index_book_city_loading.setVisibility(View.GONE);
                handler.sendEmptyMessage(LVRESET);
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                ll_index_book_city_loading.setVisibility(View.GONE);
                handler.sendEmptyMessage(LVRESET);
            }
        });


    }

    private void refreshDataFromNet(final String path) {

        HttpUtils http = new HttpUtils();
        http.send(HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // Log.e("TAG", responseInfo.result);
                SpUtils.getInstance(context).save(path, responseInfo.result);
                Gson g = new Gson();
                BookCityBean bookBean = g.fromJson(responseInfo.result,
                        BookCityBean.class);
                bookCityList = new ArrayList<BookCityBean.BookCityData>();
                bookCityList.addAll(bookBean.getData());
                pgv_book.setAdapter(mGridAdapter);
                if (bookBean.getData().size() != 0) {
                    ll_index_book_city_loading.setVisibility(View.GONE);
                }
                ll_index_book_city_loading.setVisibility(View.GONE);
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
                    BookCityBean bookBean = g.fromJson(result,
                            BookCityBean.class);
                    bookCityList = new ArrayList<BookCityBean.BookCityData>();
                    bookCityList.addAll(bookBean.getData());
                    pgv_book.setAdapter(mGridAdapter);
                    if (bookBean.getData().size() != 0) {
                        ll_index_book_city_loading.setVisibility(View.GONE);
                    }
                }
                ll_index_book_city_loading.setVisibility(View.GONE);
                handler.sendEmptyMessage(LVRESET);
            }
        });

    }


    private void getDataFromNet(final String url) {
        Log.e("TAG", "正在访问新的图书地址  :" + url);
        HttpUtils http = new HttpUtils();
        http.send(HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // Log.e("TAG", responseInfo.result);
                Log.e("TAG", "得到的图书新地址的结果为 " + responseInfo.result);
                SpUtils.getInstance(context).save(url, responseInfo.result);
                Gson g = new Gson();
                BookCityBean bookBean = g.fromJson(responseInfo.result,
                        BookCityBean.class);
                bookCityList = new ArrayList<BookCityBean.BookCityData>();
                bookCityList.addAll(bookBean.getData());
                pgv_book.setAdapter(mGridAdapter);
                if (bookBean.getData().size() != 0) {
                    ll_index_book_city_loading.setVisibility(View.GONE);
                    Toast.makeText(context, "没有查询到相关类型的图书", Toast.LENGTH_SHORT).show();
                }
                ll_index_book_city_loading.setVisibility(View.GONE);
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                String result = SpUtils.getInstance(context).getValue(url, "");
                if (!result.equals("")) {
                    Gson g = new Gson();
                    BookCityBean bookBean = g.fromJson(result,
                            BookCityBean.class);
                    bookCityList = new ArrayList<BookCityBean.BookCityData>();
                    bookCityList.addAll(bookBean.getData());
                    pgv_book.setAdapter(mGridAdapter);
                    if (bookBean.getData().size() != 0) {
                        ll_index_book_city_loading.setVisibility(View.GONE);
                    }
                }
                ll_index_book_city_loading.setVisibility(View.GONE);

            }
        });

    }

    private void initEvent() {
        ll_index_community_book_subject.setOnClickListener(this);
        iv_index_community_book_search.setOnClickListener(this);
    }

    private void initLayout() {
        pgv_book = (PullToRefreshGridView) view.findViewById(R.id.pgv_book);
        iv_index_community_book_search = (ImageView) view.findViewById(R.id.iv_index_community_book_search);
        iv_index_community_book_subjec_arrow_down = (ImageView) view.findViewById(R.id.iv_index_community_book_subjec_arrow_down);
        ll_index_community_book_subject = (LinearLayout) view.findViewById(R.id.ll_index_community_book_subject);
        ll_index_book_city_loading = (LinearLayout) view.findViewById(R.id.ll_index_book_city_loading);
        tv_index_community_book_department = (TextView) view.findViewById(R.id.tv_index_community_book_department);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_index_community_book_search:
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

            case R.id.ll_index_community_book_subject:
                iv_index_community_book_subjec_arrow_down
                        .setImageResource(R.drawable.image_orange_up);
                final AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .create();
                alertDialog.show();
                alertDialog
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                iv_index_community_book_subjec_arrow_down
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

                gv_index_book_subject = (GridView) window
                        .findViewById(R.id.gv_index_book_subject);
                gv_index_book_subject.setVerticalSpacing(10);
                gv_index_book_subject.setHorizontalSpacing(10);
                gv_index_book_subject.setAdapter(msAdapter);
                gv_index_book_subject
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent,
                                                    View view, int position, long id) {
                                Log.e("TAG", "成功监听");
                                if (subjectSign != position) {
                                    page = 1;
                                    ll_index_book_city_loading
                                            .setVisibility(View.VISIBLE);
                                    subjectSign = position;
                                    code = subjectSign + 1;
                                    msAdapter.notifyDataSetInvalidated();
                                    Log.e("TAG", "成功调用");
                                    String path = "http://api.1xuezhe.com/book/ByClass?mid="
                                            + mid + "&page=1&cid=20&rtn=15&code=0" + code + "&sort=0";
                                    getDataFromNet(path);
                                    tv_index_community_book_department
                                            .setText(subjectContent
                                                    .get(subjectSign));
                                }
                                alertDialog.dismiss();
                            }
                        });
                break;

            default:

                break;
        }
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
            return convertView;
        }
    }

    static class mSubjectViewHolder {
        LinearLayout llContent;
        TextView tvContent;
        ImageView ivContent;
    }

    class MyGridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return bookCityList.size();
        }

        @Override
        public Object getItem(int position) {
            return bookCityList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            mGridViewHolder mGridHolder = new mGridViewHolder();
            BookCityBean.BookCityData bookInfo = bookCityList.get(position);
            if (convertView == null) {
                convertView = View.inflate(context,
                        R.layout.item_childview_index_book_city, null);
                mGridHolder.ivHead = (ImageView) convertView
                        .findViewById(R.id.iv_child_index_book_city);
                mGridHolder.tvAuthor = (TextView) convertView
                        .findViewById(R.id.tv_child_index_book_city_author);
                mGridHolder.tvTitle = (TextView) convertView
                        .findViewById(R.id.tv_child_index_book_city_title);
                convertView.setTag(mGridHolder);
            } else {
                mGridHolder = (mGridViewHolder) convertView.getTag();
            }
            mGridHolder.tvAuthor.setText(bookInfo.getAuthor());
            mGridHolder.tvTitle.setText(bookInfo.getBookname());
            BitmapUtils bitmapUtils = new BitmapUtils(context);
            bitmapUtils.display(mGridHolder.ivHead, bookInfo.getBookpic());
            return convertView;
        }
    }

    static class mGridViewHolder {
        ImageView ivHead;
        TextView tvTitle;
        TextView tvAuthor;
    }
}
