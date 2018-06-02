package com.ijays.kotlinstudy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ijays.kotlinstudy.activity.LayoutAnimationActivity
import com.ijays.kotlinstudy.extension.checkSelfPermissionCompat
import com.ijays.kotlinstudy.extension.requestPermissionCompat
import com.ijays.kotlinstudy.extension.shouldShowRequestPermissionRationalCompat
import com.ijays.kotlinstudy.util.ToastUtil
import kotlinx.android.synthetic.main.content_main.*

const val REQUEST_PERMISSION_CAMERA = 1

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val fab = findViewById(R.id.fab) as FloatingActionButton

        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        bt_open_camera.setOnClickListener(this)
        bt_layout_animation.setOnClickListener(this)

    }

    /**
     *打开相机
     */
    private fun openCamera() {

        if (checkSelfPermissionCompat(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //如果授予了权限
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();

        } else {

            if (shouldShowRequestPermissionRationalCompat(Manifest.permission.CAMERA)) {
                ToastUtil.showShort(this, "弹窗")

                requestPermissionCompat(arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION_CAMERA)
            } else {

                val snackBar = Snackbar.make(content_view, R.string.request_permission, Snackbar.LENGTH_SHORT)
                snackBar.setAction(R.string.OK, {

                    ToastUtil.showShort(it.context, "我在使用Kotlin")
                }).show()

                requestPermissionCompat(arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION_CAMERA)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_PERMISSION_CAMERA -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权成功
                    Snackbar.make(content_view, R.string.grant_success, Snackbar.LENGTH_SHORT).show()
                } else {
                    //授权失败
                    Snackbar.make(content_view, R.string.grant_failed, Snackbar.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_open_camera -> openCamera()

            R.id.bt_layout_animation -> {
                startActivity(Intent(this@MainActivity,LayoutAnimationActivity::class.java))
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
