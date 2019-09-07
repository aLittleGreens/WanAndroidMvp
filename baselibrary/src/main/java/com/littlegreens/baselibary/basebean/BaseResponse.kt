package com.littlegreens.baselibary.basebean

import java.io.Serializable

/**
 * des:封装服务器返回数据
 *
 */
abstract class BaseResponse<T : Any?> {
    var code: Int = 0
    var msg: String = ""
    abstract var data: T?

    override fun toString(): String {
        return "BaseResponse{" +
                "code='" + code + '\''.toString() +
                ", msg='" + msg + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}
