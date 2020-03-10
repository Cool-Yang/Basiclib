package com.yzg.basiclib.binding

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

/**
 * Created by yangzhigang
 * on 2020/3/9
 * View通用事件绑定封装类
 */

//View单击事件监听别名定义
typealias onClick = (View) -> Unit
//默认单击事件间隔时间
const val DEFAULT_THROTTLE_TIME = 500L

/**
 * [View]防抖动点击事件
 *
 * @param onClick 点击事件消费者
 * @param time 防抖动时间间隔，单位ms
 */
@SuppressLint("CheckResult")
@BindingAdapter("bind_onClick", "bind_throttleFirst", requireAll = false)
fun setOnClick(view: View, click: onClick, time: Long = DEFAULT_THROTTLE_TIME) {
    RxView.clicks(view)
        .throttleFirst(time , TimeUnit.MILLISECONDS)
        .subscribe {click.invoke(view) }
}

/**
 * [View]是否可见
 *
 * @param visible 值为true时可见
 */
@BindingAdapter("bind_visibility")
fun setVisible(view: View, visible: Boolean) {
    RxView.visibility(view).accept(visible)
}

/**
 * [View]设置背景图片
 *
 * @param resId 资源id
 */
@BindingAdapter("bind_back_ground")
fun setImageRes(view: View, resId: Int) {
    view.setBackgroundResource(resId)
}


