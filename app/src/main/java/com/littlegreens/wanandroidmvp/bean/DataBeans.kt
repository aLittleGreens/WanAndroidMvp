package com.littlegreens.wanandroidmvp.bean

import com.littlegreens.baselibary.basebean.BaseResponse

/**
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019-09-07 17:54
 */


/********************* Begin: wxArticle Data ***********************/
data class WxArticle(override var data : List<WxDataBean>?) : BaseResponse<List<WxDataBean>>()

data class WxDataBean(var id: Int,var name: String)
/********************* End: wxArticle Data ***********************/
