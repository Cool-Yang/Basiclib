package com.yzg.basiclib.view.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yzg.basiclib.view.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * Created by yangzhigang
 * on 2020/3/9
 * DataBinding ViewModel结合使用的Activity
 */
abstract class BaseModelActivity<V : ViewDataBinding, VM : BaseViewModel> : BaseDataBindingActivity<V>() {
    protected var viewModel: VM? = null

    override fun initViewDataBinding() {
        super.initViewDataBinding()
        bindDefViewModel()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        registorUIChangeLiveDataCallBack()
        initViewObservable()
    }

    /**
     * 创建ViewModel及绑定(与xml的绑定和生命周期的回调)
     * */
    private fun bindDefViewModel() {
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
            viewModel = createViewModel(this@BaseModelActivity,modelClass) as? VM
        }
        //DataBinding 绑定ViewModel
        val viewModelId = initVariableId()
        binding.setVariable(viewModelId, viewModel)
        viewModel?.let {
            lifecycle.addObserver(it)
        }
    }

    /**
     * 公共事件回调监听(自添加可重写该方法 但注意保留super)
     * */
    private fun registorUIChangeLiveDataCallBack() {

        //跳入新页面
        viewModel?.startActivityEvent?.observe(this@BaseModelActivity, Observer {
            val clz = it[BaseViewModel.ParameterField.CLASS] as Class<*>
            val bundle = it[BaseViewModel.ParameterField.BUNDLE] as? Bundle
            startActivity(clz, bundle)
        })

        //跳入新页面并回调
        viewModel?.startActivityForResultEvent?.observe(this@BaseModelActivity, Observer {
            val clz = it[BaseViewModel.ParameterField.CLASS] as Class<*>
            val bundle = it[BaseViewModel.ParameterField.BUNDLE] as? Bundle
            val code = it[BaseViewModel.ParameterField.REQUEST_CODE] as? Int
            startActivityForResult(clz, bundle, code!!)
        })

        //关闭界面
        viewModel?.finishEvent?.observe(this@BaseModelActivity, Observer {
            finish()
        })

        //关闭界面并回调
        viewModel?.finishResultEvent?.observe(this@BaseModelActivity, Observer {
            setResult(BaseViewModel.ParameterField.DEF_REQUEST_CODE)
            finish()
        })

    }

    /**
     * 自定义的业务通知监听
     * */
    open fun initViewObservable() {
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
    protected open fun initViewModel(): VM? {
        return null
    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    private fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity).get(cls)
    }

    override fun onDestroy() {
        super.onDestroy()
        //移除监听
        viewModel?.let {
            lifecycle.removeObserver(it)
        }
    }
}