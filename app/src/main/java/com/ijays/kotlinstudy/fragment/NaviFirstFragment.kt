package com.ijays.kotlinstudy.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_navi_first.*

/**
 * Created by ijays on 2019/1/2.
 */
class NaviFirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_navi_first, container, false)
        v.findViewById<Button>(R.id.bt_jump_to_second)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_first_to_naviSecondFragment)
        }
        return v
    }

}