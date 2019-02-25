package com.ijays.kotlinstudy.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ijays.kotlinstudy.AppConstants
import com.ijays.kotlinstudy.R

/**
 * Created by ijays on 2019/1/2.
 */
class NaviThirdFragment : Fragment() {
    companion object {
        fun newInstance(type: Int): NaviThirdFragment {
            val bundle = Bundle()
            bundle.putInt(AppConstants.APP_DATA, type)
            val fragment = NaviThirdFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var pageType = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageType = arguments?.getInt(AppConstants.APP_DATA) ?: 0

        val v = inflater.inflate(R.layout.fragment_navi_third, container, false)

        arguments?.let {
            val param = it.getString("test_param")
            v.findViewById<TextView>(R.id.tv_param_display).text = if (param?.isNotEmpty() == true) param else ""

        }

        val back = v.findViewById<TextView>(R.id.bt_back_fragment_first)
        back?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_naviThirdFragment_to_nav_first)
        }

        if (pageType != 0) {
            back.visibility = View.GONE
        }
        return v
    }
}