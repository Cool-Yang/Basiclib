package com.yzg.basiclib.network.retrofit.exception

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 自定义异常类 所有的异常都会转换为该类型
 * code    错误码
 * erroMsg 错误信息
 */

data class ApiException constructor(val code : Int,val erroMsg : String) : Exception()