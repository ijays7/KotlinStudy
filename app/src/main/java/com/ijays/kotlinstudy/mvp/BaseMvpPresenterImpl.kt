package com.ijays.kotlinstudy.mvp

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Created by ijays on 2018/6/2.
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> constructor(var view: V?) : BaseMvpPresenter<V> {

    override fun onCreate(owner: LifecycleOwner) {
        Log.e("SONGJIE", "====>ONCREATE====${owner.lifecycle.currentState}")
        attachView(view)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        detachView()
        Log.e("SONGJIE", "====>ONDESTROY====${owner.lifecycle.currentState}")
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        Log.e("SONGJIE", "====>onLIFECYCLERCHANGED====$event")
    }


    override fun attachView(view: V?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

}