package com.yzg.basiclib.logger

import timber.log.Timber

/**
 * Created by yangzhigang
 * on 2020/3/9
 * 日志打印扩展工具类
 */
typealias Supplier<T> = () -> T

fun initLogger(isDebug: Boolean = true) {
    Timber.tag("TradPsd")

    if (isDebug)
        Timber.plant(Timber.DebugTree())
    else
        Timber.plant(CrashReportingTree())

    log { "initLogger successfully, isDebug = $isDebug" }
}

inline fun log(supplier: Supplier<String>) =
    logd(supplier)

inline fun logd(supplier: Supplier<String>) = Timber.d(supplier())

inline fun logi(supplier: Supplier<String>) = Timber.i(supplier())

inline fun logw(supplier: Supplier<String>) = Timber.w(supplier())

inline fun loge(supplier: Supplier<String>) = Timber.e(supplier())