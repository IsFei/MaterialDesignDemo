package com.gaofei.materialdesignd;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by lenovo on 2015/9/24.
 */
public class NewAty extends Activity {
    private Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

//        // 允许使用transitions
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//
//// 设置一个exit transition
//        getWindow().setExitTransition(new Explode());
        button= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transition ts = new ChangeTransform();
                ts.setDuration(3000);
                getWindow().setExitTransition(ts);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(NewAty.this,
                        new Pair<View, String>(button,"shareName"),
                        new Pair<View, String>(button2,"shareName2"));
                Intent intent = new Intent(NewAty.this, RActivity.class);
                startActivity(intent, options.toBundle());
            }
        });
    }
}
