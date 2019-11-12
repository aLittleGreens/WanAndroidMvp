package com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean

import com.littlegreens.baselibary.basebean.BaseResponse

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/12 16:14
 */

data class BannerBeanList(override var data: List<BannerBean>?) : BaseResponse<List<BannerBean>>()

data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)
//{
//    "desc": "一起来做个App吧",
//    "id": 10,
//    "imagePath": "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
//    "isVisible": 1,
//    "order": 0,
//    "title": "一起来做个App吧",
//    "type": 0,
//    "url": "https://www.wanandroid.com/blog/show/2"
//}