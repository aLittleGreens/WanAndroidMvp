package com.littlegreens.baselibary.base

import android.app.Application
import android.content.Context
import com.littlegreens.baselibary.commonutil.SPUtil

/**
 *
 *
 * @author LittleGreens <a href="mailto:alittlegreens@foxmail.com">Contact me.</a>
 * @version 1.0
 * @since 2019/9/5 18:02
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        SPUtil.init(applicationContext)


    }

    companion object {
        var appContext: Context? = null
    }

}