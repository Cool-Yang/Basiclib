package com.yzg.basiclib.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yzg.basiclib.utils.ActivityUtil
import com.gyf.immersionbar.ktx.immersionBar

/**
 * Created by yangzhigang
 * on 2020/3/9
 * Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mContext: Context

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //添加到Activity管理列表
        ActivityUtil.instance.addActivity(this)
        mContext = this
        //页面接受的参数方法
        initParam()
        setView()
        initView()
    }

    /**
     * 获取上个界面传递的参数
     * */
    protected open fun initParam() {

    }

    /**
     * 设置界面
     * */
    protected open fun setView() {
        setContentView(initContentView())
        toolBarFitSystemWindows()
    }


    /**
     * 设置状态栏(沉浸式)
     * */
    protected open fun toolBarFitSystemWindows() {
        immersionBar()
    }

    /**
     * 子类在此方法中进行初始化等操作
     * */
    protected open fun initView() {}


    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clz)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivity(intent)
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected fun startActivityForResult(clz: Class<*>, bundle: Bundle?, code: Int) {
        val intent = Intent(this, clz)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivityForResult(intent, code)
    }

    /**
     * 界面销毁
     * */
    override fun onDestroy() {
        super.onDestroy()
        //移除该Activity
        ActivityUtil.instance.finishActivity(this)
    }

}