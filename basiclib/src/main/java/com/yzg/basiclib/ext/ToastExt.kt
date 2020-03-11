package com.yzg.basiclib.ext

import android.content.Context
import android.widget.Toast
import com.yzg.basiclib.utils.Utils

/**
 * Created by yangzhigang
 * on 2020/3/10
 * Toast扩展类
 */

fun toast(duration: Int = Toast.LENGTH_SHORT, value: () -> String) =
    instance(duration,value()).show()

//fun toast(id : Int,duration: Int = Toast.LENGTH_SHORT) =
//    instance(duration,ResUIUtils.getString(id)).show()

inline fun Context.toast(duration: Int = Toast.LENGTH_SHORT, noinline value: () -> String) =
    instance(duration, value()).show()


fun instance(duration: Int, value:String): Toast {
//    log { value }
//    val toast = Toast(BaseApplication.INSTANCE)
//    val rootView = View.inflate(BaseApplication.INSTANCE, R.layout.bg_toast,null)
//    toast.view = rootView
//    val mTextView = rootView.findViewById<TextView>(R.id.toast)
//    mTextView.text = value
//    toast.duration = duration
//    return toast
    return Toast(Utils.getApp())
}
