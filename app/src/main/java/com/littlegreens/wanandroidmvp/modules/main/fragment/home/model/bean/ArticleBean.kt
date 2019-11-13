package com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean

import com.littlegreens.baselibary.basebean.BaseResponse

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/11/12 16:21
 */

data class ArticleBeanTopList(override var data: List<ArticleBean>?) :
    BaseResponse<List<ArticleBean>>()

data class ArticleBean(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<TagsBean>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)

//{
//    "apkLink": "",
//    "audit": 1,
//    "author": "网易",
//    "chapterId": 361,
//    "chapterName": "课程推荐",
//    "collect": false,
//    "courseId": 13,
//    "desc": "",
//    "envelopePic": "",
//    "fresh": true,
//    "id": 8904,
//    "link": "https://url.163.com/4bj",
//    "niceDate": "刚刚",
//    "niceShareDate": "未知时间",
//    "origin": "",
//    "prefix": "",
//    "projectLink": "",
//    "publishTime": 1575043200000,
//    "selfVisible": 0,
//    "shareDate": null,
//    "shareUser": "",
//    "superChapterId": 249,
//    "superChapterName": "干货资源",
//    "tags": [],
//    "title": "利用OpenCV对图片进行处理，快速实战识别图文信息",
//    "type": 1,
//    "userId": -1,
//    "visible": 1,
//    "zan": 0
//}

data class TagsBean(
    var name: String,
    var url: String
)
/**
 * name : 公众号
 * url : /wxarticle/list/410/1
 */

