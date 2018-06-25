package com.ijays.kotlinstudy.model

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ijays.kotlinstudy.R
import com.ijays.kotlinstudy.extension.parseTimeStamp2String
import com.ijays.kotlinstudy.flow.browser.BrowserActivity
import com.ijays.kotlinstudy.util.ToastUtil

/**
 * Created by ijays on 2018/6/4.
 */
class ArticleListAdapter(private var dataList: MutableList<ArticleInfoModel>) :
        RecyclerView.Adapter<ArticleListAdapter.ArticleListVH>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleListVH {
        return ArticleListVH(LayoutInflater.from(parent?.context).inflate(R.layout.item_article_list,
                parent, false))
    }

    override fun onBindViewHolder(holder: ArticleListVH?, position: Int) {
        holder?.show(dataList.get(position))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getAdapter(): MutableList<ArticleInfoModel> {
        return dataList
    }

    class ArticleListVH(val view: View) : RecyclerView.ViewHolder(view) {
        private val tv_title: TextView = view.findViewById(R.id.tv_title)
        private val tv_author: TextView = view.findViewById(R.id.tv_author)
        private val tv_time: TextView = view.findViewById(R.id.tv_time)
        private val tv_chapter_name: TextView = view.findViewById(R.id.tv_chapter_name)


        fun show(articleInfoModel: ArticleInfoModel) {
            tv_title.text = articleInfoModel.title
            tv_author.text = articleInfoModel.author
            tv_chapter_name.text = articleInfoModel.chapterName ?: ""
            tv_time.text = articleInfoModel.publishTime.parseTimeStamp2String(articleInfoModel.publishTime)

            itemView.setOnClickListener({

                BrowserActivity.startActivity(it.context, articleInfoModel.link)
            })
        }


    }
}