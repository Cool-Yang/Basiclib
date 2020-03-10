package com.yzg.basiclib.network.retrofit.ext

import androidx.lifecycle.Lifecycle
import com.yzg.basiclib.view.viewmodel.LifecycleViewModel
import com.uber.autodispose.*
import io.reactivex.*

/**
 * Created by yangzhigang
 * on 2020/3/10
 * 绑定生命周期扩展类
 */

fun <T> Observable<T>.bindLifecycle(
    lifecycleViewModel: LifecycleViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): ObservableSubscribeProxy<T> =
    bindLifecycleEvent(
        lifecycleViewModel.lifecycleOwner
            ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
        lifecycleEvent
    )


fun <T> Flowable<T>.bindLifecycle(
    lifecycleViewModel: LifecycleViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): FlowableSubscribeProxy<T> =
    bindLifecycleEvent(
        lifecycleViewModel.lifecycleOwner
            ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
        lifecycleEvent
    )

fun <T> Single<T>.bindLifecycle(
    lifecycleViewModel: LifecycleViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): SingleSubscribeProxy<T> =
    bindLifecycleEvent(
        lifecycleViewModel.lifecycleOwner
            ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
        lifecycleEvent
    )

fun <T> Maybe<T>.bindLifecycle(
    lifecycleViewModel: LifecycleViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): MaybeSubscribeProxy<T> =
    bindLifecycleEvent(
        lifecycleViewModel.lifecycleOwner
            ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
        lifecycleEvent
    )

fun Completable.bindLifecycle(
    lifecycleViewModel: LifecycleViewModel,
    lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): CompletableSubscribeProxy =
    bindLifecycleEvent(
        lifecycleViewModel.lifecycleOwner
            ?: throw throwableWhenLifecycleOwnerIsNull(lifecycleViewModel),
        lifecycleEvent
    )

private fun throwableWhenLifecycleOwnerIsNull(viewModel: LifecycleViewModel): NullPointerException =
    NullPointerException("$viewModel's lifecycleOwner is null.")