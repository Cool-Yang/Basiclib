package com.yzg.basiclib.network.retrofit.data

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 返回数据基类 如果字段名称不一致 可以继承该类重写返回值
 */

open class IResponse<T> {

    /**
     * 响应吗
     * @return
     */
    open var code: Int = -1

    /**
     * 异常消息
     * @return
     */
    open var message: String = ""

    /**
     * 返回数据
     * @return
     */
    protected open var data: T? = null

    /**
     * 接口返回的时间戳，单位毫秒
     * */
    protected open var ts: Long = 0

    /**
     * 请求是否成功
     * @return
     */
    open val success: Boolean
        get() {
            return code == 0
        }

    /**
     * 对数据进行转换 也就是包装成Optional类型
     * */
    open fun transform() : Optional<T> {
        return Optional(data)
    }

    override fun toString(): String {
        return "IResponse(code='$code', msg='$message', data=$data, success=$success)"
    }


}