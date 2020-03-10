package com.yzg.basiclib.network.retrofit.exception

import android.net.ParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 异常转换工具类 将各种异常统一转换为ApiException
 */

object ExceptionEngine {
    //未知错误
    private const val UN_KNOWN_ERROR = 1000
    //解析数据错误
    private const val ANALYTIC_SERVER_DATA_ERROR = 1001
    //服务器错误
    private const val SERVER_ERROR = 1004
    //网络连接错误
    private const val CONNECT_ERROR = 1002
    //网络连接超时
    private const val TIME_OUT_ERROR = 1003
    //网络错误
    const val NET_ERROR = 1005

    fun handleException(e: Throwable): ApiException {

        return when (e) {
            is HttpException -> {
                ApiException(NET_ERROR, "网络错误")
            }

            is ServerException -> {
                ApiException(e.code, e.erroMsg)
            }

            is com.alibaba.fastjson.JSONException, is JSONException, is ParseException -> {
                ApiException(ANALYTIC_SERVER_DATA_ERROR, "解析错误")
            }

            is ConnectException -> {
                ApiException(CONNECT_ERROR, "连接失败")
            }

            is SocketTimeoutException -> {
                ApiException(TIME_OUT_ERROR, "网络超时")
            }

            else -> {
                val erroMsg = e.message
                if (erroMsg.isNullOrEmpty())
                    ApiException(UN_KNOWN_ERROR, "未知错误")
                else
                    ApiException(UN_KNOWN_ERROR, erroMsg)
            }
        }

    }


}