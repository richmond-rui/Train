package com.lanlengran.train.Image;

import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 芮勤 on 18-10-17 17:01
 */
public class DataUtils {
    /**
     * 将String数据存为文件
     */
    public static File wtiteFileFromBytes(String name, String path) {
        byte[] b=name.getBytes();
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(path);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    public static void saveImage(Bitmap btImage){
        if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)) // 判断是否可以对SDcard进行操作
        {	  // 获取SDCard指定目录下
            String  sdCardDir = Environment.getExternalStorageDirectory()+ "/myImage/";
            File dirFile  = new File(sdCardDir);  //目录转化成文件夹
            if (!dirFile .exists()) {				//如果不存在，那就建立这个文件夹
                dirFile .mkdirs();
            }							//文件夹有啦，就可以保存图片啦
            File file = new File(sdCardDir, "my.jpg");// 在SDcard的目录下创建图片文,以当前时间为其命名
            FileOutputStream out=null;
            try {
                out = new FileOutputStream(file);
                btImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
