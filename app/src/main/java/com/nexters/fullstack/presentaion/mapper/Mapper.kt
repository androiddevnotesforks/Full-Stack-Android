package com.nexters.fullstack.presentaion.mapper

interface Mapper<T, E> {
    fun toData(item: E): T

    fun fromData(item: T): E
}