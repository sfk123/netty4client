package com.sheng.example;

/**
 * Created by Administrator on 2017/5/7.
 */
public class NettyClient {



    public static void main(String[] args) throws Exception {
        // Configure SSL.git

        for(int i=0;i<100;i++) {
            new Thread(new BootstarapRunnable(i)).start();
        }
    }
}
