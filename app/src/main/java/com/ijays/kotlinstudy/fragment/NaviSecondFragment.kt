package com.ijays.kotlinstudy.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ijays.kotlinstudy.R
import kotlinx.android.synthetic.main.fragment_navi_second.*

/**
 * Created by ijays on 2019/1/2.
 */
class NaviSecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_navi_second, container, false)

        v.findViewById<TextView>(R.id.bt_jump_to_third)?.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("test_param", "TEST_PARAM")

            Navigation.findNavController(it).navigate(R.id.action_naviSecondFragment_to_naviThirdFragment, bundle)
        }


        v.findViewById<TextView>(R.id.bt_back)?.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SONGJIE", "NaviSecond  onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("SONGJIE", "NaviSecond  onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("SONGJIE", "NaviSecond  onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("SONGJIE", "NaviSecond  onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("SONGJIE", "NaviSecond  onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("SONGJIE", "NaviSecond  onDestroy")
    }
}