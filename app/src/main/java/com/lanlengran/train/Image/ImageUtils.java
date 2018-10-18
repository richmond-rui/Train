package com.lanlengran.train.Image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.lanlengran.train.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 芮勤 on 18-10-15 14:00
 */
public class ImageUtils {
    private static final String TAG = "ImageUtils";

    /**
     * 图片二值化
     * @param bitmap
     * @param threshold
     * @return
     */
    public static Bitmap Image2SigleColor(Bitmap bitmap, int threshold) {

        StringBuffer stringBuffer = new StringBuffer();
        int[] oldPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        bitmap.getPixels(oldPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int[] newPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        int size = 0;
        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getWidth(); x++) {

                int px = oldPx[x + bitmap.getWidth() * y];
                int b = Color.blue(px);
                int g = Color.green(px);
                int r = Color.red(px);

                int light = (r + g + b) / 3;
                //二值化
                if (light > threshold) {
                    newPx[x + bitmap.getWidth() * y] = Color.argb(0xff, 0xff, 0xff, 0xff);
                } else {
                    newPx[x + bitmap.getWidth() * y] = Color.argb(0xff, 0x00, 0x00, 0x00);
                }

            }
            size++;
        }
        Log.d(TAG, "image2String: x==" + size);
        Log.d(TAG, "image2String: height==" + bitmap.getHeight());
        Log.d(TAG, "image2String: width==" + bitmap.getWidth());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        newBitmap.setPixels(newPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return newBitmap;
    }

    /**
     * 黑白照片
     * @param bitmap
     * @return
     */
    public static Bitmap image2BlackColor(Bitmap bitmap) {
        StringBuffer stringBuffer = new StringBuffer();
        int[] oldPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        bitmap.getPixels(oldPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int[] newPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        int size = 0;
        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getWidth(); x++) {

                int px = oldPx[x + bitmap.getWidth() * y];
                int b = Color.blue(px);
                int g = Color.green(px);
                int r = Color.red(px);

                int light = (r + g + b) / 3;
                //二值化
//                if (light > 0xff / 3 * 2) {
//                    newPx[x + bitmap.getWidth() * y] = Color.argb(0xff, 0xff, 0xff, 0xff);
//                } else {
//                    newPx[x + bitmap.getWidth() * y] = Color.argb(0xff, 0x00, 0x00, 0x00);
//                }

                newPx[x + bitmap.getWidth() * y] = Color.argb(0xff, light, light, light);
            }
            size++;
        }
        Log.d(TAG, "image2String: x==" + size);
        Log.d(TAG, "image2String: height==" + bitmap.getHeight());
        Log.d(TAG, "image2String: width==" + bitmap.getWidth());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        newBitmap.setPixels(newPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return newBitmap;
    }

    /**
     * 负片效果
     * @param bitmap
     * @return
     */
    public static Bitmap image2Negative(Bitmap bitmap) {
        StringBuffer stringBuffer = new StringBuffer();
        int[] oldPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        bitmap.getPixels(oldPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int[] newPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        int size = 0;
        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getWidth(); x++) {

                int px = oldPx[x + bitmap.getWidth() * y];
                int b = Color.blue(px);
                int g = Color.green(px);
                int r = Color.red(px);

                int light = (r + g + b) / 3;

                newPx[x + bitmap.getWidth() * y] = Color.argb(0xff, 0xff - r, 0xff - g, 0xff - b);
//                int newb=b+30<255?b+10:255;
//                int newg=g+30<255?g+10:255;
//                int newr=r+30<255?r+10:255;
//                newPx[x + bitmap.getWidth() * y] = Color.argb(0xff, newr, newg, newb);
            }
            size++;
        }
        Log.d(TAG, "image2String: x==" + size);
        Log.d(TAG, "image2String: height==" + bitmap.getHeight());
        Log.d(TAG, "image2String: width==" + bitmap.getWidth());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        newBitmap.setPixels(newPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return newBitmap;
    }

    /**
     * 把图片转成byte
     * @param bitmap
     * @return
     */
    public static byte[] image2Bytes(Bitmap bitmap) {
        int newHeight = bitmap.getHeight();
        int newWidth = bitmap.getWidth() % 8 == 0 ? bitmap.getWidth() / 8 : bitmap.getWidth() / 8 + 1;

        byte[] dataBytes = new byte[newHeight * newWidth];
        byte[] heightBytes = ByteUtils.IntToByteArray(newHeight);
        byte[] widthBytes = ByteUtils.IntToByteArray(newWidth);
        byte[] resultBytes = new byte[8 + newHeight * newWidth];


//        stringBuffer.append(strWidth);
//        stringBuffer.append(strHeight);


        int[] oldPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        bitmap.getPixels(oldPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int size = 0;
        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < newWidth; x++) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 8; i++) {
                    if (x * 8 + i < bitmap.getWidth()) {
                        int px = oldPx[x * 8 + i + bitmap.getWidth() * y];
                        int b = Color.blue(px);
                        int g = Color.green(px);
                        int r = Color.red(px);

                        int light = (r + g + b) / 3;
                        if (light > 0xff / 3 * 2) {
                            sb.append(1);
                        } else {
                            sb.append(0);
                        }
                    } else {
                        sb.append(0);
                    }
                }

                byte c = (byte) (Integer.parseInt(sb.reverse().toString(), 2) & 0xff);
                dataBytes[x + y * newWidth] = c;
            }
            size++;
        }
        Log.d(TAG, "image2String: x==" + size);
//        Log.d(TAG, "stringBuffer.size: x==" + stringBuffer.length());
        System.arraycopy(widthBytes, 0, resultBytes, 0, 4);
        System.arraycopy(heightBytes, 0, resultBytes, 4, 4);
        System.arraycopy(dataBytes, 0, resultBytes, 8, dataBytes.length);
        return resultBytes;
    }

    /**
     * 把字符串转成图片
     * @param resultBytes
     * @return
     */
    public static Bitmap string2Image(byte[] resultBytes) {


        byte[] heightBytes = new byte[4];
        byte[] widthBytes = new byte[4];

        System.arraycopy(resultBytes, 0, widthBytes, 0, 4);
        System.arraycopy(resultBytes, 4, heightBytes, 0, 4);

        int width = ByteUtils.ByteArrayToInt(widthBytes);
        int height = ByteUtils.ByteArrayToInt(heightBytes);
        int realWidth = width * 8;

        byte[] dataBytes = new byte[height * width];
        System.arraycopy(resultBytes, 8, dataBytes, 0, height * width);


        int[] newPx = new int[realWidth * height];
        Log.d(TAG, "string2Image: width===" + width);
        Log.d(TAG, "string2Image: height===" + height);
        int size = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                byte c = dataBytes[x + y * width];

                for (int i = 0; i < 8; i++) {
                    int index = x * 8 + i + realWidth * y;
//                    size=index;
//                    int px=c>>i|0x01;
                    int px = c >> i & 0x01;

                    if (px == 1) {
//                        Log.d(TAG, "string2Image: px=="+px);
                        newPx[index] = Color.argb(0xff, 0xff, 0xff, 0xff);
                    } else {
                        newPx[index] = Color.argb(0xff, 0x00, 0x00, 0x00);
                    }

                }
//                int index=x+realWidth*y;
//                size=index;
//                newPx[index]=Color.argb(0xff, 0x00, 0x00, 0x00);
            }
        }
        Log.d(TAG, "string2Image: size==" + (size + 1));
        Log.d(TAG, "string2Image: needSize==" + realWidth * height);
//        for (int i=0;i<newPx.length;i++){
//            newPx[i]=Color.argb(0xff, 0x00, 0x00, 0x00);
//        }
//        for (char c:string.toCharArray()){
//            if (c=='\n'){
//
//            }else {
//               String str= Integer.toBinaryString(c);
//                Log.d(TAG, "string2Image: str==="+str);
//               for (char px:str.toCharArray()){
//                   if (px==1){
//                       integerList.add( Color.argb(0xff, 0xff, 0xff, 0xff));
//                   }else {
//                       integerList.add(Color.argb(0xff, 0x00, 0x00, 0x00));
//                   }
//               }
//            }
//        }
//
//
//
//        Log.d(TAG, "string2Image: integerList==="+integerList.size());
//        Log.d(TAG, "string2Image: width*height==="+width*height);

//
//        for (int i = 0; i < integerList.size(); i++) {
//            newPx[i] = integerList.get(i);
//        }
//        Log.d(TAG, "string2Image: string=="+string);
        Bitmap newBitmap = Bitmap.createBitmap(realWidth, height, Bitmap.Config.ARGB_8888);
        newBitmap.setPixels(newPx, 0, realWidth, 0, 0, realWidth, height);
        return newBitmap;
    }

    /**
     * 图片二值化
     * @param bitmap
     * @param threshold
     * @param gap
     * @return
     */
    public static Bitmap Image2String(Bitmap bitmap, Context context, int threshold, int gap) {

        StringBuilder stringBuilder = new StringBuilder();
        int[] oldPx = new int[bitmap.getHeight() * bitmap.getWidth()];
        bitmap.getPixels(oldPx, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int size = 0;
        for (int y = 0; y < bitmap.getHeight(); y=y+gap) {
            for (int x = 0; x < bitmap.getWidth(); x=x+gap) {

                int px = oldPx[x + bitmap.getWidth() * y];
                int b = Color.blue(px);
                int g = Color.green(px);
                int r = Color.red(px);

                int light = (r + g + b) / 3;
                //二值化
                if (light > threshold) {
                    stringBuilder.append("    ");
                } else {
                    stringBuilder.append("娟");
                }

            }
            size++;
            stringBuilder.append("\n");
        }
        Log.d(TAG, "Image2String: size=="+size);
        return drawTextToBitmap(context,stringBuilder,gap);
    }

    public static Bitmap drawTextToBitmap(Context mContext,   StringBuilder text,int gap) {
        try {
            gap*=4;
            int width=text.indexOf("\n")+1;
            int height=text.length()/width;
            Log.d(TAG, "Image2String: height=="+height);
            Log.d(TAG, "Image2String: width=="+width);
            String mText=text.toString();
            Resources resources = mContext.getResources();
            Bitmap bitmap = Bitmap.createBitmap(width*gap/2,height*gap,Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);

            canvas.drawColor(Color.WHITE);


            TextPaint mCurrentPaint =new TextPaint();

            mCurrentPaint.setColor(Color.BLACK);

            mCurrentPaint.setTextAlign(Paint.Align.CENTER);

            mCurrentPaint.setTextSize(gap);

            StaticLayout currentLayout = new StaticLayout(mText,mCurrentPaint,canvas.getWidth(),

                    Layout.Alignment.ALIGN_NORMAL, 1f, 0f,true);

            canvas.translate(canvas.getWidth()/2 ,0 );

            currentLayout.draw(canvas);


            return bitmap;
        } catch (Exception e) {
            // TODO: handle exception



            return null;
        }

    }

//    private void Image2String(final Bitmap bitmap, int gap) {
//        //        clors=new int[bitmap.getHeight()*bitmap.getHeight()];
//        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
//        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
//        //  Bitmap bitmap2=Bitmap.createBitmap(pixels,0,bitmap.getWidth(),bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.RGB_565);
//
//
//        // int gap=80;
//        Log.d("qin", "getWidth==========>" + bitmap.getWidth());
//        Log.d("qin", "gap==========>" + gap);
//        final StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < bitmap.getHeight() - gap; i = i + gap) {
//            for (int j = 0; j < bitmap.getWidth() - gap; j = j + gap) {
//                int color = pixels[i * bitmap.getWidth() + j];
//                int r = Color.red(color);
//                int g = Color.green(color);
//                int b = Color.blue(color);
//                if ((r + g + b) < 380) {
//                    sb.append("蓝");
//                } else {
//                    sb.append("。");
//                }
//            }
//            sb.append("\n");
//        }
//        //  Log.d("qin",sb.toString());
//        Log.d("qin", sb.toString().length() + "");
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                imageView.setImageBitmap(bitmap);
//                textView.setText(sb.toString());
//                textView.setTextSize(DensityUtils.px2dip(MainActivity.this, gap));
//            }
//        });
//
//    }

}
