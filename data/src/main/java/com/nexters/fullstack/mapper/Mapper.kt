package com.nexters.fullstack.mapper

interface Mapper<D, T> {
    fun toDomain(data: D): T

    fun fromDomain(data: T): D
}