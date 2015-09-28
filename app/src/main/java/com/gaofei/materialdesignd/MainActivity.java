package com.gaofei.materialdesignd;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getActionBar().setTitle("HHHHHH");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // 创建一个线性布局管理器
        mLayoutManager = new LinearLayoutManager(this);
        //设置横向
//        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLayoutManager.canScrollHorizontally();
        mLayoutManager.canScrollVertically();
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 创建数据集
        dataset= new ArrayList<String>();
        for (int i = 0; i <20; i++) {
            dataset.add(i,"item"+ (i+1));
        }
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(dataset, this);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                removeItem(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        ((FloatingActionButton)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(3);
            }
        });
    }

    private void addItem(int n) {
        for (int i = 0; i <n; i++) {
            dataset.add(i,"item1"+ (i));
        }
        mAdapter.notifyItemRangeInserted(0,n);
        mRecyclerView.smoothScrollToPosition(0);
    }

    private void removeItem(int position) {
        dataset.remove(position);
        mAdapter.notifyItemRemoved(position);
    }



}