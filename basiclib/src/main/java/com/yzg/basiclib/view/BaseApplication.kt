package com.yzg.basiclib.view

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.yzg.basiclib.logger.initLogger

open class BaseApplication : Application() {

    companion object {
        //全局的Application对象
        lateinit var INSTANCE: Application
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        //初始化日志打印
        initLogger(BuildConfig.DEBUG)
        initApp()
    }

    //自定义的Application初始化 重写该方法即可
    protected open fun initApp(){}
}