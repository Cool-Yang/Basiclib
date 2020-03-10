package com.yzg.basiclib.network.okhttp

import okhttp3.Dns
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit

/**
 * Created by yangzhigang
 * on 2020/3/10
 *
 */

class XDns(private val timeout: Long) : Dns {

    override fun lookup(hostname: String): List<InetAddress> {
        return if (hostname == null) {
            throw UnknownHostException("网络错误")
        } else {
            try {
                val task = FutureTask(
                    Callable { listOf(*InetAddress.getAllByName(hostname)) })
                Thread(task).start()
                task.get(timeout, TimeUnit.MILLISECONDS)
            } catch (var4: Exception) {
                val unknownHostException = UnknownHostException("网络错误")
                unknownHostException.initCause(var4)
                throw unknownHostException
            }

        }
    }
}