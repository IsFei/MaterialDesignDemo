package com.gaofei.materialdesignd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.Toolbar;

/**
 * Created by lenovo on 2015/9/9.
 */
public class RActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("DEMO");
        toolbar.setSubtitle("Material Design");
        toolbar.setNavigationIcon(R.drawable.btn_arrows);
        setActionBar(toolbar);
//        setNavigationIcon在setActionBar方法后
        // Menu item click 要在 setActionBar 后才有作用
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition ts = new ChangeTransform();
//                ts.setDuration(3000);
                getWindow().setExitTransition(ts);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RActivity.this, button, "shareName");
                Intent intent = new Intent(RActivity.this, NewAty.class);
                startActivity(intent, options.toBundle());
            }
        });


        // 允许使用transitions
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // 设置一个exit transition
//        getWindow().setExitTransition(new Explode());

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RActivity.this, MainActivity.class));
            }
        });


        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RActivity.this, NewAty.class),ActivityOptions.makeSceneTransitionAnimation(RActivity.this).toBundle());
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
                    msg += "Click share";
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
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
