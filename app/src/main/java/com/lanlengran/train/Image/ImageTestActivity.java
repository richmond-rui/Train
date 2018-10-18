package com.lanlengran.train.Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanlengran.train.MainActivity;
import com.lanlengran.train.R;

public class ImageTestActivity extends AppCompatActivity {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private TextView textView;
    int gap =1;
    private long startTime;
    private static final String TAG = "ImageTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);

        imageView1=findViewById(R.id.image1);
        imageView2=findViewById(R.id.image2);
        imageView3=findViewById(R.id.image3);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.timg);
     //   Bitmap bitmap=BitmapFactory.decodeFile("/sdcard/DCIM/Camera/erweima.jpg");
     //  Bitmap bitmap=BitmapFactory.decodeFile("/sdcard/erweima4.jpg");
 //      Bitmap newBitmap=  ImageUtils.Image2SigleColor(bitmap,70);

//        byte[] s=ImageUtils.image2Bytes(bitmap);
//        ByteUtils.savePhotoToSDCardByte(s,"/sdcard/imageTest/","timg");
//        byte[] s2=ByteUtils.getPhotoBySDCardByte("/sdcard/imageTest/","timg");
//        Bitmap newBitmap2=ImageUtils.string2Image(s2);

//        startTime=System.currentTimeMillis();
//        Bitmap newBitmap3=ImageUtils.ImageFindQR(bitmap);
//        Log.d(TAG, "onCreate: 旧方法耗时"+(System.currentTimeMillis()-startTime));

//        startTime=System.currentTimeMillis();
//        Bitmap newBitmap2=ImageFindQrUtils.ImageFindQR(bitmap,50);
//        Log.d(TAG, "onCreate: 新方法耗时"+(System.currentTimeMillis()-startTime));

        imageView2.setImageBitmap(bitmap);
 //       imageView2.setImageBitmap(newBitmap);
  //      imageView3.setImageBitmap(newBitmap2);

 //       imageView1.setVisibility(View.GONE);
  //      imageView2.setVisibility(View.GONE);
     //   imageView3.setVisibility(View.GONE);

        textView=findViewById(R.id.textView);
        gap= DensityUtils.dip2px(this, 2);
        Bitmap str=ImageUtils.Image2String(bitmap,this,170,gap);

        DataUtils.saveImage(str);
        str=null;
    //    imageView1.setImageBitmap( str);
        imageView1.setImageBitmap(ImageUtils.Image2SigleColor(bitmap,170));
    }


}
