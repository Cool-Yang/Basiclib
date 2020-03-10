package com.yzg.basiclib.network.retrofit.ext

import com.yzg.basiclib.network.retrofit.data.IResponse
import com.yzg.basiclib.network.retrofit.data.Optional
import com.yzg.basiclib.network.retrofit.function.HttpResultFunction
import com.yzg.basiclib.network.retrofit.scheduler.handleResult
import com.yzg.basiclib.network.retrofit.scheduler.observableIO2Main
import io.reactivex.Observable

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 发送者组成扩展类
 */

fun <T> Observable<out IResponse<T>>.sendCompose(): Observable<Optional<T>> =
    compose(handleResult())
    .onErrorResumeNext(HttpResultFunction())
    .compose(observableIO2Main())
