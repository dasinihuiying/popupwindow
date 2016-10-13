package com.example.day26_3;
//    http://zhushou.72g.com/app/gift/gift_list/
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.TextAdapter;
import entity.Gift;
import task.TextTask;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Gift> list;
    private TextAdapter textAdapter;
   // private
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setData();
        setAdapter() ;

    }

    private void setAdapter() {
        textAdapter = new TextAdapter(this,list);
        listView.setAdapter(textAdapter);
    }

    private void setData() {
        list = new ArrayList<>();
      new TextTask(new TextTask.TextCallback() {
          @Override
          public void callBack(List<Gift> gifts) {
                list.addAll(gifts);
              listView.deferNotifyDataSetChanged();
          }
      }).execute("http://zhushou.72g.com/app/gift/gift_list/");
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
    }
}
