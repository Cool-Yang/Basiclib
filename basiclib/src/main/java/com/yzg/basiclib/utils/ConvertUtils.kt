package com.yzg.basiclib.utils

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 单位转换工具类
 */

object ConvertUtils {

    /**
     * dp 转 px
     *
     * @param dpValue dp 值
     * @return px 值
     */
    fun dp2px(dpValue: Float): Int {
        val scale = Utils.getApp().resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px 转 dp
     *
     * @param pxValue px 值
     * @return dp 值
     */
    fun px2dp(pxValue: Float): Int {
        val scale = Utils.getApp().resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * sp 转 px
     *
     * @param spValue sp 值
     * @return px 值
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = Utils.getApp().resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * px 转 sp
     *
     * @param pxValue px 值
     * @return sp 值
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = Utils.getApp().resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

}