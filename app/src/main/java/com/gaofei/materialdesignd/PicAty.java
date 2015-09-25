package com.gaofei.materialdesignd;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by lenovo on 2015/9/25.
 */
public class PicAty extends Activity{
    private ImageView mImageView;
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        mImageView= (ImageView) findViewById(R.id.iv_p);
        mLinearLayout= (LinearLayout) findViewById(R.id.linear);

        //
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bg7);
        //用palette取色
        Palette.generateAsync(bmp, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                if (null != swatch) {
                    //getRgb()返回的是该色板上的一个rbg颜色
                    // getTitleTextColor()返回的android建议的一个用于任何标题的颜色
                    // getBodyTextColor()返回的是android建议的一个用于任何body的颜色
                    mLinearLayout.setBackgroundColor(colorBurn(swatch.getRgb()));

//                    Window window = getWindow();
//                    window.setStatusBarColor(colorBurn(swatch.getRgb()));
//                    window.setNavigationBarColor(colorBurn(swatch.getRgb()));
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *            Android中我们一般使用它的16进制，
     *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
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
