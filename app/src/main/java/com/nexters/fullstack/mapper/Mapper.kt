package com.nexters.fullstack.mapper

interface Mapper<T, E> {
    fun toData(item: E): T

    fun fromData(item: T): E
}