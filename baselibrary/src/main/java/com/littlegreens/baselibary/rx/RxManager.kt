package com.lish.base.klaus.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.HashMap

/**
 * Created by littleGreens on 2019/5/7.
 */
class RxManager {

    private val mRxBus = RxBus.instance
    /**
     * 管理上下游订阅关系
     */
    private val mCompositeDisposable = CompositeDisposable()
    /**
     * 管理Rxbus订阅
     */
    private val mObservableMap = HashMap<String, Observable<*>>()

    /**
     * 注入事件监听
     *
     * @param eventName
     * @param consumer
     * @param <T>
    </T> */
    fun <T : Any> on(eventName: String, consumer: Consumer<T>) {
        val observable = mRxBus.register<T>(eventName)
        mObservableMap[eventName] = observable
        mCompositeDisposable.add(observable.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, Consumer<Throwable> { throwable -> throwable.printStackTrace() }))

    }

    /**
     * 收集这个Disposable，便于终断上下游事件
     *
     * @param m
     */
    fun add(m: Disposable) {
        /*订阅管理*/
        mCompositeDisposable.add(m)
    }

    /**
     * 生命周期结束，取消订阅和所有Rxbus观察
     */
    fun clear() {
        mCompositeDisposable.dispose()//切断订阅事件
        for ((key, value) in mObservableMap) {
            // 移除rxbus观察
            mRxBus.unRegister(key, value)
        }
    }

}