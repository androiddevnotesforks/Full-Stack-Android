package com.nexters.fullstack

sealed class Exceptions : Throwable(){
    abstract val errorMessage : String
}

object NotFoundViewType : Exceptions() {
    override val errorMessage: String = "존재하지 않는 ViewType 입니다."
}