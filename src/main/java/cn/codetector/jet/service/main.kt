package cn.codetector.jet.service

import cn.codetector.jet.service.network.NettyClient
import cn.codetector.jet.service.network.TestHandler
import cn.codetector.jet.std.network.decoder.ByteToRawpacketDecoder
import cn.codetector.jet.std.network.encoder.JsonPacketEncoder
import cn.codetector.jet.std.network.encoder.PuretextPacketEncoder
import cn.codetector.jet.std.network.encoder.RawPacketEncoder

/**
 * Created by Codetector on 2017/3/12.
 * Project Jet
 */

fun main(args: Array<String>) {
    NettyClient { channel ->
        channel.pipeline()
                // Decoders
                .addLast(ByteToRawpacketDecoder())
                // Encoders
                .addLast(RawPacketEncoder())
                .addLast(PuretextPacketEncoder())
                .addLast(JsonPacketEncoder())
                // Handlers
                .addLast(TestHandler())
    }.connect("localhost", 19578)
}