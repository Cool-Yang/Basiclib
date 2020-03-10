package com.yzg.basiclib.network.retrofit.function

import android.util.Log
import com.yzg.basiclib.network.retrofit.exception.ExceptionEngine
import com.yzg.basiclib.BuildConfig
import io.reactivex.annotations.NonNull

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 异常转换 将异常进行转换
 */

class HttpResultFunction<T> : io.reactivex.functions.Function<Throwable, io.reactivex.Observable<T>> {
    override fun apply(@NonNull throwable: Throwable): io.reactivex.Observable<T> {
        //打印具体错误
        if (BuildConfig.DEBUG)
            Log.e("HttpResultFunction:", throwable.toString())
        return io.reactivex.Observable.error(ExceptionEngine.handleException(throwable))
    }
}