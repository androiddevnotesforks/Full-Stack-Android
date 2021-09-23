package com.nexters.fullstack.data.mapper

interface Mapper<D, T> {
    fun toDomain(data: D): T

    fun fromDomain(data: T): D
}