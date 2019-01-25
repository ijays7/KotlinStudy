package com.ijays.kotlinstudy.extension

import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * Created by ijays on 2019/1/25.
 */
fun <T> bindToLifecycle(lifecycleOwner: LifecycleOwner?): AutoDisposeConverter<T> {
    return AutoDispose.autoDisposable<T>(AndroidLifecycleScopeProvider.from(lifecycleOwner))
}