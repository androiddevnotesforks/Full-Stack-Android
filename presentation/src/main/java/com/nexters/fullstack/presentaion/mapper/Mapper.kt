package com.nexters.fullstack.presentaion.mapper

interface Mapper<T, D> {
    fun toData(data: T): D

    fun fromData(data: D): T
}