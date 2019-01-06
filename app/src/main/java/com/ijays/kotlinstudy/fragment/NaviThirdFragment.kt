package com.ijays.kotlinstudy.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ijays.kotlinstudy.R

/**
 * Created by ijays on 2019/1/2.
 */
class NaviThirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_navi_third, container, false)

        arguments?.let {
            val param = it.getString("test_param")
            v.findViewById<TextView>(R.id.tv_param_display).text = if (param?.isNotEmpty() == true) param else ""

        }

        v.findViewById<TextView>(R.id.bt_back_fragment_first)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_naviThirdFragment_to_nav_first)
        }
        return v
    }
}