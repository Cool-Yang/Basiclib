package com.yzg.demo.api

import com.yzg.basiclib.network.BaseApi

/**
 * Created by yangzhigang
 * on 2020/3/10
 *
 */

val apiService: ApiService = BaseApi.api.initRetrofit("BASE_URL").create(ApiService::class.java)
