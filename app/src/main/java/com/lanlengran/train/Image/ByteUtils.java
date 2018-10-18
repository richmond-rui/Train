package com.lanlengran.train.Image;

import android.graphics.Bitmap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 芮勤 on 18-10-15 16:54
 */
public class ByteUtils {
    /*将int转为低字节在前，高字节在后的byte数组
b[0] = 11111111(0xff) & 01100001
b[1] = 11111111(0xff) & (n >> 8)00000000
b[2] = 11111111(0xff) & (n >> 8)00000000
b[3] = 11111111(0xff) & (n >> 8)00000000
*/
    public static byte[] IntToByteArray(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    //将低字节在前转为int，高字节在后的byte数组(与IntToByteArray1想对应)
    public static int ByteArrayToInt(byte[] bArr) {
        if (bArr.length != 4) {
            return -1;
        }
        return (int) ((((bArr[3] & 0xff) << 24)
                | ((bArr[2] & 0xff) << 16)
                | ((bArr[1] & 0xff) << 8)
                | ((bArr[0] & 0xff) << 0)));
    }

    public static void savePhotoToSDCardByte(byte[] bytes,String path,String photoName){

            File dir = new File(path);
            if (!dir.exists()){
                dir.mkdirs();
            }
            if(bytes !=null)
            {
                byte[] byteArray = bytes;
                File photoFile = new File(path , photoName);
                FileOutputStream fileOutputStream = null;
                BufferedOutputStream bStream = null;
                try {
                    fileOutputStream = new FileOutputStream(photoFile);
                    bStream = new BufferedOutputStream(fileOutputStream);
                    bStream.write(byteArray);
                } catch (FileNotFoundException e) {
                    photoFile.delete();
                    e.printStackTrace();
                } catch (IOException e) {
                    photoFile.delete();
                    e.printStackTrace();
                } finally{
                    try {
                        bStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public static byte[] getPhotoBySDCardByte(String path,String photoName){
        byte[] bytes=null;
        File file = new File(path+photoName);
        if (!file.exists()){
            return new byte[0];
        }
        FileInputStream fileInputStream = null;
        BufferedInputStream bStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bytes=toByteArray(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;

    }
    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}
