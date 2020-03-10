package com.yzg.basiclib.view.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.yzg.basiclib.view.viewmodel.IViewModel

/**
 * Created by yangzhigang
 * on 2020/3/9
 * 具体生命周期ViewModel
 */
open class LifecycleViewModel : ViewModel(),
    IViewModel {
    var lifecycleOwner: LifecycleOwner? = null

    @CallSuper
    override fun onCreate(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    @CallSuper
    override fun onStart(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner

    }

    @CallSuper
    override fun onResume(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner

    }

    @CallSuper
    override fun onPause(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner

    }

    @CallSuper
    override fun onStop(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner

    }

    @CallSuper
    override fun onDestroy(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = null
    }

    @CallSuper
    override fun onLifecycleChanged(
        lifecycleOwner: LifecycleOwner,
        event: Lifecycle.Event
    ) {}
}