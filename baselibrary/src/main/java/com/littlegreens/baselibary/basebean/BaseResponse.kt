package com.littlegreens.baselibary.basebean

/**
 * des:封装服务器返回数据
 *
 */
abstract class BaseResponse<T : Any?> {
    var errorCode: Int = 0
    var errorMsg: String = ""
    abstract var data: T?

    fun isSuccess() = errorCode == 0


    override fun toString(): String {
        return "BaseResponse{" +
                "code='" + errorCode + '\''.toString() +
                ", msg='" + errorMsg + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}
