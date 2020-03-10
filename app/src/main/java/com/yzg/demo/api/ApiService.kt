package com.yzg.demo.api

import com.yzg.basiclib.network.retrofit.data.IResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 请求接口类
 */

interface ApiService {

    @GET("aa/bbb")
    fun test(): Observable<IResponse<String>>

}