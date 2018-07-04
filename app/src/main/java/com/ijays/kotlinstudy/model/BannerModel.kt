package com.ijays.kotlinstudy.model

/**
 * Created by ijays on 2018/7/4.
 */


//"desc": "",
//"id": 4,
//"imagePath": "http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png",
//"isVisible": 1,
//"order": 0,
//"title": "看看别人的面经，搞定面试~",
//"type": 1,
//"url": "http://www.wanandroid.com/article/list/0?cid=73"
data class BannerModel(val id: Int,
                       val isVisible: Int,
                       val order: Int,
                       val type: Int,
                       val desc: String,
                       val imagePath: String,
                       val title: String,
                       val url: String)