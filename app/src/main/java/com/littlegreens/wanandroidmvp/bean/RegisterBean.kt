package com.littlegreens.wanandroidmvp.bean

import com.littlegreens.baselibary.basebean.BaseResponse

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/10 11:23
 */
data class RegisterBean(override var data: RegisterData?) : BaseResponse<RegisterData?>()

data class RegisterData(
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