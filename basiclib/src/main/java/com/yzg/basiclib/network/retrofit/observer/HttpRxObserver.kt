package com.yzg.basiclib.network.retrofit.observer

import com.yzg.basiclib.network.retrofit.exception.ApiException
import com.yzg.basiclib.network.retrofit.exception.ExceptionEngine
import io.reactivex.observers.DisposableObserver

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 通用基础Observer
 */
abstract class HttpRxObserver<T> : DisposableObserver<T>() {

    override fun onError(e: Throwable) {
        when (e) {
            is ApiException -> {
                onError(e)
            }

            else -> {
                onError(ExceptionEngine.handleException(e))
            }
        }
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onComplete() {

    }

    /**
     * 错误/异常回调
     */
    protected abstract fun onError(e: ApiException)

    /**
     * 成功回调
     */
    protected abstract fun onSuccess(data: T)

}