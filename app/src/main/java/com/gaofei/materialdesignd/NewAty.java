package com.gaofei.materialdesignd;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by lenovo on 2015/9/24.
 */
public class NewAty extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("NewAty");
        toolbar.setSubtitle("DrawerLayout放在toolbar下面");
        setSupportActionBar(toolbar);


        //设置抽屉DrawerLayout
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(NewAty.this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        //设置导航栏NavigationView的点击事件
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //图片的点击事件
        mImageView= (ImageView) findViewById(R.id.iv_blue);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(NewAty.this, mImageView, "pic");
                Intent intent=new Intent(NewAty.this,PicAty.class);


                startActivity(intent,options.toBundle());
            }
        });

//        View view = mNavigationView.inflateHeaderView(R.layout.navigation_header);   想改headerview里面的颜色，此方法失败了
////        ((RelativeLayout)view.findViewById(R.id.navigation_view_head)).setBackgroundResource(R.color.primary_dark_material_light);
//        RelativeLayout relativeLayout= (RelativeLayout) view.findViewById(R.id.navigation_view_head);
//        relativeLayout.setBackgroundColor(Color.BLUE);


    }

}
