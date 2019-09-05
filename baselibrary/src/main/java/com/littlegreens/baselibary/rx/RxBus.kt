package com.lish.base.klaus.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by littleGreens on 2019/5/7.
 */
class RxBus private constructor() {
    /**
     * String -> 事件key值
     * List<Subject> -> 集合中的观察者都在观察这个事件</Subject>
     */
    private val mSubjectMapper = ConcurrentHashMap<String, MutableList<Subject<*>>>()

    companion object {
        private var mRxBus: RxBus? = null
        val instance: RxBus
            get() {
                if (mRxBus == null) {
                    synchronized(RxBus::class.java) {
                        if (mRxBus == null) {
                            mRxBus = RxBus()
                        }
                    }
                }
                return mRxBus!!
            }
    }

    /**
     * 注册事件源
     *
     * @param tag
     * @param <T>
     * @return
    </T> */
    fun <T : Any> register(tag: String): Observable<T> {

        var subjectList: MutableList<Subject<*>>? = mSubjectMapper[tag]

        if (subjectList == null) {
            subjectList = ArrayList()
            mSubjectMapper[tag] = subjectList
        }
        val subject: Subject<T> = PublishSubject.create<T>()
        subjectList.add(subject)
        return subject
    }

    /**
     * 解绑事件
     *
     * @param tag
     */
    fun unRegister(tag: String) {
        val subjectList = mSubjectMapper[tag]
        subjectList?.apply {
            this.clear()
            mSubjectMapper.remove(tag)
        }
    }

    /**
     * 解绑指定观察者
     *
     * @param tag
     * @param observable
     */
    fun unRegister(tag: String, observable: Observable<*>) {
        val subjectList = mSubjectMapper[tag]
        subjectList?.takeIf {
            it.contains(observable)
        }?.also {
            subjectList.remove(observable)
            if (subjectList.isEmpty()) {
                mSubjectMapper.remove(tag)
            }
        }
    }

    /**
     * 触发事件
     *
     * @param tag     事件对应的key值
     * @param content 要传递出去的对象
     */
    fun post(tag: String, content: Any) {
        val subjects = mSubjectMapper[tag]
        subjects as MutableList<Subject<Any>>
        subjects?.apply {
            for (subjuct in subjects) {
                subjuct.onNext(content)
            }
        }
    }

}
