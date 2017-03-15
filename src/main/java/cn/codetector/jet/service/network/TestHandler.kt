package cn.codetector.jet.service.network

import cn.codetector.jet.jetsimplejson.JSONObject
import cn.codetector.jet.std.network.packet.JsonPacket
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

/**
 * Created by Codetector on 2017/3/12.
 * Project Jet
 */
class TestHandler : ChannelInboundHandlerAdapter(){
    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.writeAndFlush(JsonPacket(JSONObject().put("test","testVal")))
        println("sent")
    }
}