package com.nexters.fullstack

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


object BusImpl {
    private val subject = PublishSubject.create<Int>()

    fun publish(): Observable<Int> = subject

    fun sendData(data: Int) {
        subject.onNext(data)
    }

    val resultCode: Int
        get() = _resultCode

    internal var _resultCode = 0
}