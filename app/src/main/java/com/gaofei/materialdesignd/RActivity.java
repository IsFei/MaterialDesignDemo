package com.gaofei.materialdesignd;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lenovo on 2015/9/9.
 */
public class RActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r);

        //设置toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("DEMO");
        toolbar.setSubtitle("Material Design");
//        setsActionBar(toolbar);
        setSupportActionBar(toolbar);
//        setNavigationIcon在setActionBar方法后,不然出现三个横线那个图标
//        toolbar.setNavigationIcon(R.drawable.btn_arrows);
        // Menu item click 要在 setActionBar 后才有作用
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        toolbar.isOverflowMenuShowing();


        //设置抽屉DrawerLayout
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(RActivity.this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //设置导航栏NavigationView的点击事件
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_one:
                        toolbar.setTitle("表情");
                        break;
                    case R.id.item_two:
                        toolbar.setTitle("图片");
                        break;
                    case R.id.item_three:
                        toolbar.setTitle("卡包");
                        break;
                }
                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });

//Activity之间的跳转   当你要关闭第二个Activity时，要反转过渡动画，那么可以调用Activity.finishAfterTransition()方法,而不是Activity.finish()
        //1共同元素的跳转
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Transition ts = new ChangeTransform();
//                ts.setDuration(3000);
//                getWindow().setExitTransition(ts);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RActivity.this, button, "shareName");
                Intent intent = new Intent(RActivity.this, NewAty.class);
                startActivity(intent, options.toBundle());
            }
        });

        //2共同元素的跳转
        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition ts = new ChangeTransform();
                ts.setDuration(3000);
                getWindow().setExitTransition(ts);
                //需要用pair"绑"一下
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RActivity.this,
                        new Pair<View, String>(button, "shareName"),
                        new Pair<View, String>(button3, "shareName2"));
                Intent intent = new Intent(RActivity.this, NewAty.class);
                startActivity(intent, options.toBundle());
            }
        });

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RActivity.this, MainActivity.class));
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RActivity.this, NewAty.class), ActivityOptions.makeSceneTransitionAnimation(RActivity.this).toBundle());
            }
        });
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_delete:
                    msg += "Click delete";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(RActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 为了让 Toolbar 的 Menu 有作用，这行代码不可以拿掉
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
