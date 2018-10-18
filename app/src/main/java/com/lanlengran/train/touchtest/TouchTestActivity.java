package com.lanlengran.train.touchtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.lanlengran.train.R;

public class TouchTestActivity extends AppCompatActivity {
    private static final String TAG = "TouchTestActivity";
    private TestBtn testBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_test);
        testBtn= findViewById(R.id.btn_test);
//
//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: ");
//            }
//        });
   //     testBtn.setEnabled(false);
      //  testBtn.setLongClickable(false);
//        testBtn.post(new Runnable() {
//            @Override
//            public void run() {
//                int width=testBtn.getMeasuredWidth();
//                int height=testBtn.getMeasuredHeight();
//                Log.d(TAG, "onWindowFocusChanged: width="+width);
//                Log.d(TAG, "onWindowFocusChanged: height="+height);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewTreeObserver viewTreeObserver=testBtn.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width=testBtn.getMeasuredWidth();
                int height=testBtn.getMeasuredHeight();
                Log.d(TAG, "onWindowFocusChanged: width="+width);
                Log.d(TAG, "onWindowFocusChanged: height="+height);
            }
        });
    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus){
//            int width=testBtn.getMeasuredWidth();
//            int height=testBtn.getMeasuredHeight();
//            Log.d(TAG, "onWindowFocusChanged: width="+width);
//            Log.d(TAG, "onWindowFocusChanged: height="+height);
//        }
//    }
}
