package com.yzg.basiclib.network.okhttp

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.internal.platform.Platform

/**
 * Created by yangzhigang
 * on 2020/3/10
 * okhttp相关配置
 */


/**
 * 日志拦截器
 * */
val httpLoggingInterceptor: LoggingInterceptor
    get() {
        return LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .build()
    }
