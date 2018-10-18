package com.lanlengran.train.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lanlengran.train.R;

public class MyViewTestActivity extends AppCompatActivity {
    private ListView listView1,listView2,listView3;
    private String[] names=new String[]{"textView,EditText,Button","RadioButton","timeTest","滑动动画","点击事件分发","图像测试","View测试"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_test);
        listView1=findViewById(R.id.list1);
        listView2=findViewById(R.id.list2);
        listView3=findViewById(R.id.list3);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        listView1.setAdapter(arrayAdapter);
        listView2.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,names));
        listView3.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,names));
    }
}
