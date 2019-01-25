package com.ijays.kotlinstudy.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ijays.kotlinstudy.R
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by ijays on 2019/1/2.
 */
class NaviFirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_navi_first, container, false)
        v.findViewById<Button>(R.id.bt_jump_to_second)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_first_to_naviSecondFragment)
        }

        testAutoDispose()
        return v
    }

    /**
     * 使用 autoDispose 来自动解除 rxjava 的订阅
     */
    private fun testAutoDispose() {
        Observable.interval(3, TimeUnit.SECONDS)
                .doOnDispose {
                    Log.e("SONGJIE", "the stream has been disposed")
                }
                .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe {
                    Log.e("SONGJIE", "received number===>$it")
                }
    }

}