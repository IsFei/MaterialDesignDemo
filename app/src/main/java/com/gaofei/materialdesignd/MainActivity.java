package com.gaofei.materialdesignd;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


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
        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++) {
            dataset[i] = "item" + i;
        }
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // 当点击actionbar上的添加按钮时，向adapter中添加一个新数据并通知刷新
//            case R.id.action_add:
//
//                return true;
//            // 当点击actionbar上的删除按钮时，向adapter中移除最后一个数据并通知刷新
//            case R.id.action_remove:
//
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}