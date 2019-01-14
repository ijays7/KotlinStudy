package com.ijays.kotlinstudy.mvp

import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by ijays on 2018/6/2.
 */
interface BaseMvpPresenter<in V : BaseMvpView> : LifecycleObserver {

    fun attachView(view: V?)

    fun detachView()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(@NonNull owner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(@NonNull owner: LifecycleOwner)

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onLifecycleChanged(@NonNull owner: LifecycleOwner,
                           @NonNull event: Lifecycle.Event)
}