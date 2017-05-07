package com.sheng.example;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Administrator on 2017/5/7.
 */
public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {
    private int id;

    /**
     * Creates a client-side handler.
     */
    public HelloWorldClientHandler(int id) {
        this.id=id;
    }


    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        JSONObject json = new JSONObject();
                        json.put("type", "<----------------------haha--------------------------->" + id + "--END");
                        ctx.writeAndFlush(json.toJSONString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        //        ctx.write(msg);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
