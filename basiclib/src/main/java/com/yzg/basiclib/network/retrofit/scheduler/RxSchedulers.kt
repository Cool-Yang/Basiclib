package com.yzg.basiclib.network.retrofit.scheduler

import com.yzg.basiclib.network.retrofit.data.IResponse
import com.yzg.basiclib.network.retrofit.data.Optional
import com.yzg.basiclib.network.retrofit.exception.ServerException
import com.yzg.basiclib.network.retrofit.function.HttpResultFunction
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by yangzhigang
 * on 2020/3/10
 * Observable转换及调度工具类
 */

/**
 * 对Observable进行转换处理 所有的发送者在调用的时候都应该调用此方法
 * */
fun <T> getObservable(apiObservable: Observable<out IResponse<T>>): Observable<Optional<T>> {
    return apiObservable
        .compose(handleResult())
        .onErrorResumeNext(HttpResultFunction())
        .compose(observableIO2Main())

}

/**
 * http请求结果处理方法
 *
 * @param <T>
 * @return
</T> */
 fun <T> handleResult(): ObservableTransformer<IResponse<T>, Optional<T>> {
    return ObservableTransformer { upstream ->
        upstream
            .flatMap {
                if (it.success) {
                    createHttpData(it.transform())
                } else {
                    //这里可以根据code码做相应的处理 例如跳转到登录等 不操作直接抛出异常即可
                    Observable.error(ServerException(it.code, it.message))
                }
            }
    }
}

/**
 * 将Observable<IResponse<T>>数据转换为Optional<T>
 * */
private fun <T> createHttpData(t: Optional<T>): Observable<Optional<T>> {

    return Observable.create { e ->
        try {
            e.onNext(t)
            e.onComplete()
        } catch (exc: Exception) {
            e.onError(exc)
        }
    }
}

/**
 * 线程切换
 * */
fun <T> observableIO2Main(): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
