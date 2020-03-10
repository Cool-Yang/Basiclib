package com.yzg.basiclib.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by yangzhigang
 * on 2020/3/9
 * DataBinding Fragment
 */

abstract class BaseDataBindingFragment<V : ViewDataBinding> : BaseFragment() {
    lateinit var binding: V

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun setView(inflater: LayoutInflater, container: ViewGroup?) {
        initViewDataBinding(inflater, container)
    }

    protected open fun initViewDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, initContentView(), container, false)
        binding.lifecycleOwner = this@BaseDataBindingFragment
        mRootView = binding.root
    }

}