package cn.codetector.jet.service

import cn.codetector.jet.jetcontroller.servicemanager.communication.decoder.ByteToRawPacketDecoder
import cn.codetector.jet.jetcontroller.servicemanager.communication.encoder.JsonPacketEnc
import cn.codetector.jet.jetcontroller.servicemanager.communication.encoder.PureTextPacketEnc
import cn.codetector.jet.service.network.NettyClient
import cn.codetector.jet.service.network.TestHandler
import cn.codetector.jet.service.network.encoder.RawPacketEncoder

/**
 * Created by Codetector on 2017/3/12.
 * Project Jet
 */

fun main(args: Array<String>) {
    NettyClient{ channel ->
        channel.pipeline()
                .addLast(ByteToRawPacketDecoder())
                .addLast(RawPacketEncoder())
                .addLast(PureTextPacketEnc())
                .addLast(JsonPacketEnc())
                .addLast(TestHandler())
    }.connect("localhost", 19578)
}