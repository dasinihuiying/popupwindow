package com.example.day26_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import utils.BitmapUtils;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private Bitmap source;
    private BitmapUtils util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.imageView);
        source = BitmapFactory.decodeResource(getResources(),R.mipmap.p_3);
        util = new BitmapUtils();

    }


    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_scale:
                iv.setImageBitmap(util.getScaleImage(source,300,300));
                break;
            case R.id.btn_conpress:
                iv.setImageBitmap(util.getCompress(this,R.mipmap.p_3,200,200));
                break;
            case R.id.btn_circle:
                iv.setImageBitmap(util.getRoundBitmap(source));
                break;
            case R.id.btn_cut:
                iv.setImageBitmap(util.getCutBitmap(source,0,0,source.getWidth()/2,source.getHeight()/3));
                break;
        }
    }
}
