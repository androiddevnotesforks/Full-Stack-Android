package com.nexters.fullstack

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


object BusImpl {
    private val subject = PublishSubject.create<Any>()

    fun publish(): Observable<Any> = subject

    fun sendData(data: Any) {
        subject.onNext(data)
    }

    val resultCode: Int
        get() = _resultCode

    internal var _resultCode = 0
}