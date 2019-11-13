package com.littlegreens.wanandroidmvp.modules.main.fragment.home.adapter

import android.content.res.ColorStateList
import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.littlegreens.baselibary.glide.GlideImageLoader
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.ArticleBean


/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/13 9:48
 */


class HomeAdapter :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(com.littlegreens.wanandroidmvp.R.layout.rv_item_article) {

    private var mOnCollectViewClickListener: OnCollectViewClickListener? = null

    fun setOnCollectViewClickListener(onCollectViewClickListener: OnCollectViewClickListener) {
        mOnCollectViewClickListener = onCollectViewClickListener
    }

    override fun convert(helper: BaseViewHolder, item: ArticleBean) {
        helper.setText(com.littlegreens.wanandroidmvp.R.id.tv_title, Html.fromHtml(item.title))
        helper.setText(com.littlegreens.wanandroidmvp.R.id.tv_author, item.author)
        helper.setText(com.littlegreens.wanandroidmvp.R.id.tv_time, item.niceDate)
        helper.setText(
            com.littlegreens.wanandroidmvp.R.id.tv_super_chapter_name,
            item.superChapterName
        )
        helper.setText(com.littlegreens.wanandroidmvp.R.id.tv_chapter_name, item.chapterName)
        val ll_new = helper.getView<LinearLayout>(com.littlegreens.wanandroidmvp.R.id.ll_new)
        if (item.fresh) {
            ll_new.visibility = View.VISIBLE
        } else {
            ll_new.visibility = View.GONE
        }
        val iv_img = helper.getView<ImageView>(com.littlegreens.wanandroidmvp.R.id.iv_img)
        if (!TextUtils.isEmpty(item.envelopePic)) {
            GlideImageLoader.loadImage(iv_img, item.envelopePic)
            iv_img.visibility = View.VISIBLE
        } else {
            iv_img.visibility = View.GONE
        }
        val cv_collect = helper.getView<ImageView>(com.littlegreens.wanandroidmvp.R.id.cv_collect)
        if (item.collect) {
            cv_collect.imageTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        mContext,
                        com.littlegreens.wanandroidmvp.R.color.red
                    )
                )
        } else {
            cv_collect.imageTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        mContext,
                        com.littlegreens.wanandroidmvp.R.color.gray
                    )
                )
        }
        val tv_tag = helper.getView<TextView>(com.littlegreens.wanandroidmvp.R.id.tv_tag)
        if (item.tags != null && item.tags.isNotEmpty()) {
            tv_tag.text = (item.tags[0].name)
            tv_tag.visibility = View.VISIBLE
        } else {
            tv_tag.visibility = View.GONE
        }
        cv_collect.setOnClickListener { v ->
            if (mOnCollectViewClickListener != null) {
                mOnCollectViewClickListener!!.onClick(
                    helper,
                    v,
                    helper.adapterPosition - headerLayoutCount
                )
            }
        }
    }

    interface OnCollectViewClickListener {
        fun onClick(helper: BaseViewHolder, v: View, position: Int)
    }
}