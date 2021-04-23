package com.nexters.fullstack.source

data class HomeList(
    val type : HomeListType,
    val title : String,
    val images : List<HomeScreenshot>?
)
