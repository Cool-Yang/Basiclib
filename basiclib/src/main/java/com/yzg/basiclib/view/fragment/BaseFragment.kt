package com.yzg.basiclib.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by yangzhigang
 * on 2020/3/9
 * Fragment基类
 */

abstract class BaseFragment: Fragment() {
    protected var mContext: Context? = null
    protected lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initParam()
        setView(inflater, container)
        return mRootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected open fun setView(inflater: LayoutInflater, container: ViewGroup?) {
        mRootView = LayoutInflater.from(context).inflate(initContentView(), container, false)
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int


    /**
     * 获取上个界面传递的参数
     * */
    protected open fun initParam() {

    }

    /**
     * 初始化View
     * */
    protected open fun initView() {

    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

}