package com.lanlengran.train;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MoveAnimationActivity extends AppCompatActivity {
    Button button1;
    private Button button2;
    private ImageView imageView;
    private static final String TAG = "MoveAnimationActivity";
    private float lastX;
    private float lastY;
    private int[] btnPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_animation);
        button1=findViewById(R.id.btn1);
        imageView=findViewById(R.id.imageView);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.scrollBy(10,0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f,1f);
                animator.setDuration(2000);
                animator.start();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(button1,"translationX",0,100).setDuration(100);
                ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(button1,"translationY",0,100).setDuration(100);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(objectAnimator).with(objectAnimator2);
                animatorSet.setDuration(5000);
                animatorSet.start();
            }
        });
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();

                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        btnPos=new int[2];
        button1.getLocationInWindow(btnPos);
        Log.d(TAG, "onTouchEvent: "+event.getAction());
        Log.d(TAG, "onTouchEvent:getX "+lastX);
        Log.d(TAG, "onTouchEvent:getY "+lastY);
        Log.d(TAG, "onTouchEvent:btnPos "+btnPos[0]);
        Log.d(TAG, "onTouchEvent:btnPos "+btnPos[1]);
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            Log.d(TAG, "onTouchEvent: ACTION_MOVE");
            ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(button1,"translationX",0,event.getX()-btnPos[0]);
            ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(button1,"translationY",0,event.getY()-btnPos[1]);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimator).with(objectAnimator2);
            animatorSet.start();
        }
        lastX=event.getX();
        lastY=event.getY();
        return super.onTouchEvent(event);
    }
}
