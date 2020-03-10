package com.yzg.basiclib.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yzg.basiclib.view.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * Created by yangzhigang
 * on 2020/3/9
 *
 */

abstract class BaseModelFragment<V : ViewDataBinding, VM : BaseViewModel> : BaseDataBindingFragment<V>(){

    var viewModel: VM? = null
    private var viewModelId = -1

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.let { lifecycle.removeObserver(it) }
    }

    override fun initViewDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        super.initViewDataBinding(inflater, container)
        bindDefViewModel()
        initViewObservable()
    }

    /**
     * 绑定ViewModel
     * */
    protected open fun bindDefViewModel(){
        viewModelId = initVariableId()
        viewModel = initViewModel()
        if (viewModel == null) {
            val modelClass: Class<out ViewModel>
            val type = javaClass.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                type.actualTypeArguments[1] as Class<out ViewModel>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                BaseViewModel::class.java
            }
            viewModel = this.createViewModel(this@BaseModelFragment, modelClass) as? VM
        }

        binding.setVariable(viewModelId, viewModel)
        viewModel?.let {
            lifecycle.addObserver(it)
        }

    }

    protected open fun initViewObservable() {
        //跳入新页面
        viewModel?.startActivityEvent?.observe(this@BaseModelFragment, Observer {
            val clz = it[BaseViewModel.ParameterField.CLASS] as Class<*>
            val bundle = it[BaseViewModel.ParameterField.BUNDLE] as? Bundle
            startActivity(clz, bundle)
        })

        //关闭界面
        viewModel?.finishEvent?.observe(this@BaseModelFragment, Observer {
            activity?.finish()
        })

    }


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    open fun initViewModel(): VM? {
        return null
    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    protected fun <T : ViewModel> createViewModel(fragment: Fragment, cls: Class<T>): T {
        return ViewModelProvider(fragment).get(cls)
    }

}