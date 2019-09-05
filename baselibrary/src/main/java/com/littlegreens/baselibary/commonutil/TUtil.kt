package com.littlegreens.baselibary.commonutil

import java.lang.reflect.ParameterizedType

/**
 * Created by littleGreens on 2019/5/7.
 */
object TUtil {
    fun <T> getT(o: Any, i: Int): T? {
        try {
            return ((o.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                    .newInstance()
        } catch (e: InstantiationException) {
        } catch (e: IllegalAccessException) {
        } catch (e: ClassCastException) {
        }

        return null
    }

    fun forName(className: String): Class<*>? {
        try {
            return Class.forName(className)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        return null
    }
}