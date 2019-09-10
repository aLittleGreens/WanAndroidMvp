package com.littlegreens.wanandroidmvp.bean

import com.littlegreens.baselibary.basebean.BaseResponse

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 11:13
 */
data class LoginBean(override var data: LoginData?) : BaseResponse<LoginData?>()

/**
 * admin : false
 * chapterTops : []
 * collectIds : []
 * email :
 * icon :
 * id : 27812
 * nickname : aLittleGreens
 * password :
 * token :
 * type : 0
 * username : aLittleGreens
 */
data class LoginData(
    val admin: Boolean,
    val chapterTops: List<Any>?,
    val collectIds: List<Any>?,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)