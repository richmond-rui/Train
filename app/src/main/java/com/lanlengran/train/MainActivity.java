package com.lanlengran.train;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lanlengran.train.Image.ImageTestActivity;
import com.lanlengran.train.myview.MyViewTestActivity;
import com.lanlengran.train.touchtest.TouchTestActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] names=new String[]{"textView,EditText,Button","RadioButton","timeTest","滑动动画","点击事件分发","图像测试","View测试"};
    private Class[] classes=new Class[]{TvEtBtnActivity.class,RadioActivity.class,Main2Activity.class,MoveAnimationActivity.class,
            TouchTestActivity.class, ImageTestActivity.class, MyViewTestActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.main_listview);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,classes[position]));
            }
        });

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("test");
    }
}
