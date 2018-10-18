package com.lanlengran.train;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import static android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET;
import static android.net.NetworkCapabilities.TRANSPORT_CELLULAR;

/**
 * Created by 芮勤 on 18-10-11 10:24
 */
public class MyApplication extends Application {
    private boolean isHasNet;
    private Network network;
    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;

    @Override
    public void onCreate() {
        super.onCreate();
        initNetWork();
    }

    private void initNetWork() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (!isNetworkOnline()) {
                    Log.d("qin", "默认网络无网络连接");
                    //如果没有网络，则去连接移动网络
                    bringUpCellularNetwork();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!isHasNet) {
                                try {
                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                        return;
                                    }
                                    connectivityManager.unregisterNetworkCallback(networkCallback);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bringUpCellularNetwork();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }, 15 * 1000, 15 * 1000);
                }else{
                    Log.d("qin", "默认网络有网络连接");
                    isHasNet = true;
                    //这里初始化自己的网络访问类
//                    OkGo.getInstance().init(MyApplication.getInstance())
//                            //                                       .setOkHttpClient(builder.build())
//                            .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
//                            .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
//                            .setRetryCount(0);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

                }
            }
        }.start();
    }

    /*
     * 外网是否可以访问
     * */
    public boolean isNetworkOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 1 www.baidu.com");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void bringUpCellularNetwork() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        Log.d("qin", "bringUpCellularNetwork");
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        builder.addCapability(NET_CAPABILITY_INTERNET);
        //强制使用蜂窝数据网络-移动数据
        builder.addTransportType(TRANSPORT_CELLULAR);
        NetworkRequest networkRequest = builder.build();
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                if (!isHasNet) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        return;
                    }
                    try {
                        URL url = new URL("https://m.baidu.com/");
                        HttpURLConnection connection = (HttpURLConnection) network.openConnection(url);
                        /*******省略参数配置*******/
                        connection.connect();
                        /*******数据流处理*******/
                        if (200 == connection.getResponseCode()) {
                            //得到输入流
                            InputStream is = connection.getInputStream();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            while (-1 != (len = is.read(buffer))) {
                                baos.write(buffer, 0, len);
                                baos.flush();
                            }
                            Log.d("qin", "获取内容" + baos.toString("utf-8"));
                            if (!isHasNet) {
                                MyApplication.this.network = network;
                                isHasNet = true;
                                ConnectivityManager.setProcessDefaultNetwork(network);
                                //这里初始化自己的网络访问类
//                                OkGo.getInstance().init(MyApplication.getInstance())
//                                        //                                       .setOkHttpClient(builder.build())
//                                        .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
//                                        .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
//                                        .setRetryCount(0);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onLost(Network network) {
                super.onLost(network);
            }
        };
        connectivityManager.requestNetwork(networkRequest, networkCallback);

    }

}
