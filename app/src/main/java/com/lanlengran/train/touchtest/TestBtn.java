package com.lanlengran.train.touchtest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 芮勤 on 18-10-10 10:36
 */
public class TestBtn extends Button{
    private static final String TAG = "TestBtn";
    public TestBtn(Context context) {
        super(context);
    }

    public TestBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 用来进行事件的分发,如果事件能够传递给当前view,则此方法一定会被调用,返回结果受onTouchEvent
     * 和下级的dispatchTouchEvent影响
     * @param event
     * @return 表示是否消耗了该点击事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent: "+event.getAction());
        return super.dispatchTouchEvent(event);
    }


    /**
     * 在dispatchTouchEvent方法内部调用,用来处理点击事件,返回结果表示是否消耗当前事件,如果不消耗,
     * 则在同一个事件序列中,当前view无法再次接收到事件.
     * @param event
     * @return 表示是否消耗当前事件,
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: "+event.getAction());

        boolean isTouch=super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent: "+isTouch);
//        Log.d(TAG, "onTouchEvent: "+isClickable());
//        Log.d(TAG, "onTouchEvent: "+isLongClickable());
        return isTouch;
    }


}
