package com.yzg.basiclib.utils

import android.app.Application
import androidx.annotation.NonNull

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 主要是为了获取application 方便扩展
 */

object Utils {

    private var application: Application? = null

    /**
     * 初始化工具类
     *
     * @param app 应用
     */
    fun init(@NonNull app: Application) {
        application = app
    }

    /**
     * 获取 Application
     *
     * @return Application
     */
    fun getApp(): Application {
        if (application != null) return application!!
        throw NullPointerException("u should init first")
    }

}
