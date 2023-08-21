package org.page10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/21 21:17
 */
public class HttpFileServer {
    public void run() {
        EventLoopGroup masterGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap sb = new ServerBootstrap();
            sb.group(masterGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-decoder", new HttpResponseDecoder());
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());

                            // todo
//                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler());

                        }
                    });

            ChannelFuture sync = sb.bind("127.0.0.1", 8089).sync();
            sync.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            masterGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
