package com.nexters.data.mapper

interface Mapper<D, T> {
    fun toDomain(data: D): T

    fun fromDomain(data: T): D
}