package cn.codetector.jet.service.network

import io.netty.bootstrap.Bootstrap
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import java.net.InetAddress

/**
 * Created by Codetector on 2017/3/12.
 * Project Jet
 */
class NettyClient(val channelPipe: (SocketChannel) -> Unit) {
    lateinit var channel: Channel
        private set
    lateinit var workerGroup: NioEventLoopGroup
    fun connect(host:String, port: Int) {
        workerGroup = NioEventLoopGroup()

        val b = Bootstrap()
        b.group(workerGroup)
                .channel(NioSocketChannel::class.java)
                .handler(object : ChannelInitializer<SocketChannel>() {
                    override fun initChannel(channel: SocketChannel) {
                        channelPipe.invoke(channel)
                    }
                })
                .option(ChannelOption.SO_KEEPALIVE, true)
        val channelFuture = b.connect(host, port)
        channel = channelFuture.channel()
    }

    fun isConnected(): Boolean {
        try {
            return channel.isActive
        } catch (unInit: UninitializedPropertyAccessException) {
            return false
        }
    }

    fun disconnect() {
        if (isConnected()) {
            channel.close().sync()
            channel.closeFuture().sync()
            workerGroup.shutdownGracefully().sync()
        }
    }
}