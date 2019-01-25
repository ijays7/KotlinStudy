package com.ijays.kotlinstudy.mvp

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ijays.kotlinstudy.extension.bindToLifecycle
import com.uber.autodispose.AutoDisposeConverter

/**
 * Created by ijays on 2018/6/2.
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> constructor(var view: V?) : BaseMvpPresenter<V> {

    override fun attachView(view: V?) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun onCreate(owner: LifecycleOwner) {

        attachView(view = view)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        detachView()
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        Log.e("SONGJIE", "====>onLIFECYCLERCHANGED====$event")
    }

    /**
     * 绑定 autoDispose
     */
    fun <T> bindToLifecycle(): AutoDisposeConverter<T> {
        return bindToLifecycle(view?.getLifecycleOwner())
    }


}