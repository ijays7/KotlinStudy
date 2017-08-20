package com.ijays.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by ijaysdev on 20/08/2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
        initData()
        initView(savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    open fun initData() {

    }

    open fun initView(savedInstanceState: Bundle?) {

    }

}