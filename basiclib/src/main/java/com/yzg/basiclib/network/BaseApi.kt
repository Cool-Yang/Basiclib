package com.yzg.basiclib.network

import com.yzg.basiclib.network.okhttp.XDns
import com.yzg.basiclib.network.okhttp.httpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 基础Api 负责初始化Retrofit 设置默认OkHttpClient 如有特殊需要可以重写改类或者自己定义
 */

open class BaseApi {

    /**
     * 静态内部类单例
     */
    private object BaseApiHolder {
        val api = BaseApi()
    }

    companion object {

        val api = BaseApiHolder.api
    }

    /**
     * 初始化Retrofit
     */
    open fun initRetrofit(baseUrl: String): Retrofit {
        val builder = Retrofit.Builder()
        //支持直接格式化json返回Bean对象
        builder.addConverterFactory(FastJsonConverterFactory.create())
        //支持RxJava
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.baseUrl(baseUrl)

        var client = setClient()
        if (client == null) {
            client = defClient()
        }
        builder.client(client)

        return builder.build()
    }

    /**
     * 默认OkHttpClient设置
     *
     */
    private fun defClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .dns(XDns(5000))

        okHttpBuilder.addInterceptor(httpLoggingInterceptor)

        return okHttpBuilder.build()
    }

    /**
     * 设置OkHttpClient，添加拦截器等
     *
     * @return 可以返回为null
     */
    protected open fun setClient(): OkHttpClient? = null

}