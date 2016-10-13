package com.example.day26_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gv;
    private List<Bitmap> list;
    private GridViewAdapter adapter;
    private boolean flag = true;
    private int curPosition;
    private String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setData();
        setAdapter();
        setGridViewListener();
    }

    private void setGridViewListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag){
                    curPosition = position;
                    flag = false;
                }else {
                    Collections.swap(list,curPosition,position);
                    adapter.notifyDataSetChanged();
                    flag = true;

                    if (list.toString().equals(success)) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void setAdapter() {
        adapter = new GridViewAdapter();
        gv.setAdapter(adapter);
    }

    private void setData() {
        list = new ArrayList<>();
        //缩放到所需要的倍数
        Bitmap source = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.headimg), 333, 333, true);
        int w = source.getWidth()/3;
        int h = source.getHeight()/3;

        for (int i = 0; i < 9; i++) {
            int x = i % 3 * w;
            int y = i / 3 * h;
            Bitmap bitmap = Bitmap.createBitmap(source, x, y, w, h);
            list.add(bitmap);
        }
        success = list.toString();
    }

    private void initView() {
        gv = (GridView) findViewById(R.id.gv);
    }

    public void click(View view) {
        Collections.shuffle(list);
        adapter.notifyDataSetChanged();
    }

    private class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list == null?0:list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv ;
            if (convertView == null){
                iv = new ImageView(MainActivity.this);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
            }else {
                iv = (ImageView) convertView;
            }

            iv.setImageBitmap(list.get(position));
            return iv;
        }
    }
}
