/**
 * Project Name:netty
 * File Name:TimeClientHandler.java
 * Package Name:org.example.netty
 * Date:2018年7月19日下午6:00:46
 * Copyright (c) 2018, zengpengapple@qq.com All Rights Reserved.
 *
*/

package org.example.netty;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter{

	 @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	        ByteBuf m = (ByteBuf) msg; // (1)
	        try {
	            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
	            System.out.println(new Date(currentTimeMillis));
	            ctx.close();
	        } finally {
	            m.release();
	        }
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        cause.printStackTrace();
	        ctx.close();
	    }
	    
	    
	    public void test001() {
	    	
	    }
	
	
}

