package com.example.huangwenjian.retrofit_rxjava_mvp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huangwenjian.retrofit_rxjava_mvp.R;
import com.example.huangwenjian.retrofit_rxjava_mvp.widget.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class XListviewActivity extends Activity implements XListView.IXListViewListener {
    private XListView listview;
    private boolean b;
    private List<String> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private int count = 20;
    private BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlist);
        mInflater = LayoutInflater.from(this);

        for (int i = 0; i < 20; i++) {
            mList.add(i + "");
        }

        listview = (XListView) findViewById(R.id.listview);
        listview.setPullLoadEnable(true);
        listview.setXListViewListener(this);
        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int i) {
                return mList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = null;
                if (convertView == null) {
                    view = mInflater.inflate(R.layout.item_list, parent, false);
                } else {
                    view = convertView;
                }
                TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
                tv_item.setText(mList.get(position));
                return view;
            }
        };
        listview.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listview.stopRefresh(true);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.add(count + "");
                count++;
                mAdapter.notifyDataSetChanged();
                listview.stopLoadMore();
            }
        }, 2000);
    }
}
