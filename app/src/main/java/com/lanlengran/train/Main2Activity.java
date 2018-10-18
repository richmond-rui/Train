package com.lanlengran.train;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final TextView textView=findViewById(R.id.tv);

        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTime=textView.getText().toString();
                SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");

                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(dft.parse(strTime));

                    calendar.add(Calendar.MONTH, -1);

                    textView.setText(dft.format(calendar.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
