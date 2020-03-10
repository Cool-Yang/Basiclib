package com.yzg.basiclib.view.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.jetbrains.annotations.NotNull

/**
 * Created by yangzhigang
 * on 2020/3/9
 * ViewModel 通用生命周期接口  对应窗口的生命周期
 *
 */

interface IViewModel : LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onLifecycleChanged(@NotNull lifecycleOwner: LifecycleOwner,
                           @NotNull event: Lifecycle.Event)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(@NotNull lifecycleOwner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(@NotNull lifecycleOwner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(@NotNull lifecycleOwner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(@NotNull lifecycleOwner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(@NotNull lifecycleOwner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(@NotNull lifecycleOwner: LifecycleOwner)
}