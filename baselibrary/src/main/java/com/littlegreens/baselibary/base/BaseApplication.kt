package com.littlegreens.baselibary.base

import android.app.Application
import android.content.Context
import com.littlegreens.baselibary.commonutil.SPUtil
import me.yokeyword.fragmentation.Fragmentation

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

        initFragmentation()
    }

    private fun initFragmentation() {
        Fragmentation.builder()
            // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
            .stackViewMode(Fragmentation.BUBBLE)
            .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
            /**
             * 可以获取到[me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning]
             * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
             */
            .handleException {
                // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                // Bugtags.sendException(e);
            }
            .install()
    }

    companion object {
        var appContext: Context? = null
    }

}