package com.yzg.basiclib.view.viewmodel

import android.os.Bundle
import com.yzg.basiclib.view.viewmodel.BaseViewModel.ParameterField.DEF_REQUEST_CODE
import com.yzg.basiclib.event.SingleLiveEvent

/**
 * Created by yangzhigang
 * on 2020/3/9
 * ViewModel基类
 * 封装部分的通用通知Event
 */
open class BaseViewModel : LifecycleViewModel(){

    object ParameterField {
        const val CLASS = "CLASS"
        const val BUNDLE = "BUNDLE"
        const val REQUEST_CODE = "REQUEST_CODE"
        const val DEF_REQUEST_CODE = 10086
    }

    //界面跳转
    val startActivityEvent: SingleLiveEvent<HashMap<String, Any>> = SingleLiveEvent()
    //界面跳转 带回调
    val startActivityForResultEvent: SingleLiveEvent<HashMap<String, Any>> = SingleLiveEvent()
    //关闭界面
    val finishEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    //关闭界面 返回对应Code码
    val finishResultEvent: SingleLiveEvent<Int> = SingleLiveEvent()

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected open fun startActivity(clz: Class<*>, bundle: Bundle? = null) {
        val params = hashMapOf<String, Any>()
        params[ParameterField.CLASS] = clz
        bundle?.let {
            params[ParameterField.BUNDLE] = it
        }
        startActivityEvent.postValue(params)
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected open fun startActivityForResult(clz: Class<*>, bundle: Bundle? = null, code: Int = DEF_REQUEST_CODE) {
        val params = hashMapOf<String, Any>()
        params[ParameterField.CLASS] = clz
        bundle?.let {
            params[ParameterField.BUNDLE] = it
        }
        params[ParameterField.REQUEST_CODE] = code
        startActivityForResultEvent.postValue(params)
    }


}