package com.yzg.basiclib.network.retrofit.exception

import java.lang.RuntimeException

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 服务器异常    所有服务器返回的异常错误 都会转换为该类型 然后在转换为ApiException
 * code    错误码
 * erroMsg 错误信息
 */

data class ServerException constructor(val code : Int,val erroMsg : String) : RuntimeException()