package com.github.pwcong.jpstart.rxbus

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import rx.subjects.Subject

class RxBus private constructor() {
    private val bus: Subject<Any, Any> = SerializedSubject(PublishSubject.create())

    private object RxBusHolder {
        val sInstance: RxBus = RxBus()
    }

    companion object {
        fun getDefault(): RxBus {
            return RxBusHolder.sInstance
        }
    }

    fun post(o: Any) {
        bus.onNext(o)
    }

    fun <T> toObserverable(eventType: Class<T>?): Observable<T> {
        return bus.ofType(eventType)
    }
}