/**
 * Project Name:netty
 * File Name:DiscardServerHandler.java
 * Package Name:org.example.netty
 * Date:2018年7月19日下午4:59:55
 * Copyright (c) 2018, zengpengapple@qq.com All Rights Reserved.
 *
*/

package org.example.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;




public class DiscardServerHandler extends ChannelHandlerAdapter {

	  @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
	    
	       ctx.writeAndFlush(msg);

	       
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
	        // 当出现异常就关闭连接
	        cause.printStackTrace();
	        ctx.close();
	    }

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			
			
			    final ByteBuf time = ctx.alloc().buffer(4); // (2)
		        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

		        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
		        f.addListener(new ChannelFutureListener() {
		            @Override
		            public void operationComplete(ChannelFuture future) {
		                assert f == future;
		                ctx.close();
		            }
		        }); // (4)
		}

	
	
}

