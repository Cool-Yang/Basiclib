package com.yzg.basiclib.ext

import java.math.BigDecimal

/**
 * Created by yangzhigang
 * on 2020/3/10
 * String计算扩展
 */

/**
 * 取消科学计数法
 * */
fun String.cancelE() : String = this.toBigDecimal().toPlainString()

/**
 * 去除多余无用的0
 * */
fun String.formatZero() : String {
    var s = this
    if (s.indexOf(".") > 0) {
        //正则表达
        s = s.replace("0+?$".toRegex(), "")//去掉后面无用的零
        s = s.replace("[.]$".toRegex(), "")//如小数点后面全是零则去掉小数点
    }
    return s
}

/**
 * 先取消科学计数法在去除无用的0 二合一
 * */
fun String.format() : String = this.cancelE().formatZero()

/**
 * 格式化位数
 * @param scale       小数点保留位数
 * @param decimalType BigDecimal.setScale()方法用于格式化小数点
 * setScale(1)表示保留一位小数，默认用四舍五入方式
 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
 */
fun String.setScale(scale: Int = 2,decimalType: Int = BigDecimal.ROUND_DOWN) : String =
    this.toBigDecimal().setScale(scale, decimalType).toPlainString().formatZero()


/**
 * 金钱加法
 * @param scale       小数点保留位数
 * @param decimalType BigDecimal.setScale()方法用于格式化小数点
 * setScale(1)表示保留一位小数，默认用四舍五入方式
 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
 */
fun String.add(s : String,scale: Int = 2,decimalType: Int = BigDecimal.ROUND_DOWN) : String{
    val b1 = this.toBigDecimal()
    val b2 = s.toBigDecimal()
    return b1.add(b2).setScale(scale, decimalType).toPlainString().formatZero()
}

/**
 * 金钱减法
 * @param scale       小数点保留位数
 * @param decimalType BigDecimal.setScale()方法用于格式化小数点
 * setScale(1)表示保留一位小数，默认用四舍五入方式
 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
 */
fun String.subtract(s : String,scale: Int = 2,decimalType: Int = BigDecimal.ROUND_DOWN) : String{
    val b1 = this.toBigDecimal()
    val b2 = s.toBigDecimal()
    return b1.subtract(b2).setScale(scale, decimalType).toPlainString().formatZero()
}

/**
 * 金钱乘法
 * @param scale       小数点保留位数
 * @param decimalType BigDecimal.setScale()方法用于格式化小数点
 * setScale(1)表示保留一位小数，默认用四舍五入方式
 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
 */
fun String.multiply(s : String,scale: Int = 2,decimalType: Int = BigDecimal.ROUND_DOWN) : String{
    val b1 = this.toBigDecimal()
    val b2 = s.toBigDecimal()
    return b1.multiply(b2).setScale(scale, decimalType).toPlainString().formatZero()
}

/**
 * 金钱除法
 * @param scale       小数点保留位数
 * @param decimalType BigDecimal.setScale()方法用于格式化小数点
 * setScale(1)表示保留一位小数，默认用四舍五入方式
 * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
 * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
 * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
 * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
 */
fun String.divide(s : String,scale: Int = 2,decimalType: Int = BigDecimal.ROUND_DOWN) : String{
    val b1 = this.toBigDecimal()
    val b2 = s.toBigDecimal()
    return b1.divide(b2).setScale(scale, decimalType).toPlainString().formatZero()
}



