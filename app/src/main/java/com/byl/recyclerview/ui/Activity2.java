package com.byl.recyclerview.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.byl.recyclerview.R;
import com.byl.recyclerview.adapter.InfoAdapter;
import com.byl.recyclerview.bean.InfoBean;
import com.byl.recyclerview.view.BaseAdapter;
import com.byl.recyclerview.view.PullBaseView;
import com.byl.recyclerview.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity implements BaseAdapter.OnItemClickListener, BaseAdapter.OnItemLongClickListener,
        BaseAdapter.OnViewClickListener, PullBaseView.OnRefreshListener {

    PullRecyclerView recyclerView;
    List<Object> mDatas;
    InfoAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        initData();
        initRecyclerView();
    }

    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            InfoBean info = new InfoBean();
            info.setText("TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT" + i);
            mDatas.add(info);
        }
    }

    private void initRecyclerView() {
        recyclerView = (PullRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        infoAdapter = new InfoAdapter(this, mDatas, this);
        infoAdapter.setOnItemClickListener(this);
        infoAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(infoAdapter);
    }

    /**
     * 子View点击事件
     *
     * @param position item position
     * @param viewtype 点击的view的类型，调用时根据不同的view传入不同的值加以区分
     */
    @Override
    public void onViewClick(int position, int viewtype) {
        switch (viewtype) {
            case 1://赞
                Toast.makeText(Activity2.this, "赞-position>>" + position, Toast.LENGTH_SHORT).show();
                break;
            case 2://评论
                Toast.makeText(Activity2.this, "评论-position>>" + position, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * item点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(Activity2.this, "点击-position>>" + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * item长按事件
     *
     * @param position
     */
    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(Activity2.this, "长按-position>>" + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * 上拉加载
     *
     * @param view
     */
    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoBean info = new InfoBean();
                info.setText("更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多更多");
                mDatas.add(info);
                infoAdapter.notifyDataSetChanged();
                recyclerView.onFooterRefreshComplete();
            }
        }, 1500);
    }

    /**
     * 下拉刷新
     *
     * @param view
     */
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoBean info = new InfoBean();
                info.setText("新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增");
                mDatas.add(0, info);
                infoAdapter.notifyDataSetChanged();
                recyclerView.onHeaderRefreshComplete();
            }
        }, 1500);
    }


}
