package com.yzg.basiclib.view.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by yangzhigang
 * on 2020/3/9
 * 使用DataBinding绑定的Activity基类
 */
abstract class BaseDataBindingActivity<V : ViewDataBinding> : BaseActivity(){
    protected lateinit var binding: V

    override fun setView() {
        initViewDataBinding()
    }

    protected open fun initViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, initContentView())
        //DataBinging 绑定生命周期
        binding.lifecycleOwner = this@BaseDataBindingActivity
        //沉浸式状态栏   在设置setContentView后调用
        toolBarFitSystemWindows()
    }

    /**
     * 界面销毁
     * */
    override fun onDestroy() {
        super.onDestroy()
        //取消绑定
        binding.unbind()
    }
}