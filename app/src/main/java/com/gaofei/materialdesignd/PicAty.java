package com.gaofei.materialdesignd;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by lenovo on 2015/9/25.
 */
public class PicAty extends Activity {
    private RelativeLayout mRelativeLayout;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.linear);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.button);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bg7);
                //用palette取色
                Palette.generateAsync(bmp, new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
//                1. Palette.Swatch s1 = Palette.getVibrantSwatch(); //充满活力的色板
//                2. Palette.Swatch s2 = Palette.getDarkVibrantSwatch(); //充满活力的暗色类型色板
//                3. Palette.Swatch s3 = Palette.getLightVibrantSwatch(); //充满活力的亮色类型色板
//                4. Palette.Swatch s4 = Palette.getMutedSwatch(); //黯淡的色板
//                5. Palette.Swatch s5 = Palette.getDarkMutedSwatch(); //黯淡的暗色类型色板
//                6. Palette.Swatch s6 = Palette.getLightMutedSwatch(); //黯淡的亮色类型色板
                        Palette.Swatch swatch = palette.getLightVibrantSwatch();
                        if (null != swatch) {
                            //getRgb()返回的是该色板上的一个rbg颜色
                            // getTitleTextColor()返回的android建议的一个用于任何标题的颜色
                            // getBodyTextColor()返回的是android建议的一个用于任何body的颜色
                            mRelativeLayout.setBackgroundColor(colorBurn(swatch.getRgb()));

//                    Window window = getWindow();
//                    window.setStatusBarColor(colorBurn(swatch.getRgb()));
//                    window.setNavigationBarColor(colorBurn(swatch.getRgb()));
                        }
                    }
                });

                Animator animator = ViewAnimationUtils.createCircularReveal(mRelativeLayout,
                        mRelativeLayout.getWidth(), mRelativeLayout.getHeight(), 0,
                        (float) Math.hypot(mRelativeLayout.getWidth(), mRelativeLayout.getHeight()));
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(300);
                animator.start();

            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
