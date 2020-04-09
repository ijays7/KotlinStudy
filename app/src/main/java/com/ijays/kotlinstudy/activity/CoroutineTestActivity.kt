package com.ijays.kotlinstudy.activity

import android.os.Bundle
import android.util.Log
import com.ijays.kotlinstudy.BaseActivity
import com.ijays.kotlinstudy.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by ijays on 2020/4/8.
 */
class CoroutineTestActivity : BaseActivity() {
   companion object{
       const val TAG="Coroutine"
   }

    override fun getLayoutId() = R.layout.activity_coroutine_test_layout

    override fun initView(savedInstanceState: Bundle?) {
        GlobalScope.launch {
            // 在后台启动一个新的协程并继续
            // 等待1s 钟，默认单位为毫秒
            delay(1000L)
            Log.e(TAG,"Coroutine")
        }
        Log.e(TAG,"Hello,")
    }
}