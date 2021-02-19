package com.nexters.fullstack.util

class ColorUtil(val color : String) {
    lateinit var activeColor : String
    lateinit var inactiveColor : String
    lateinit var textColor : String
    init {
        when(color){
            "Yellow" -> {
                activeColor = "#E8C15D"
                inactiveColor = "#26E8C15D"
                textColor = "#FFE299"
            }
            "Orange" -> {
                activeColor = "#EC9147"
                inactiveColor = "#26EC9147"
                textColor = "#FFCBA1"
            }
            "Red" -> {
                activeColor = "#C76761"
                inactiveColor = "#30C76761"
                textColor = "#FFA799"
            }
            "Pink" -> {
                activeColor = "#E089B5"
                inactiveColor = "#26E089B5"
                textColor = "#FFC7E3"
            }
            "Violet" -> {
                activeColor = "#A06EE5"
                inactiveColor = "#26A06EE5"
                textColor = "#D9C2FF"
            }
            "Purple Blue" -> {
                activeColor = "#6565E5"
                inactiveColor = "#266565E5"
                textColor = "#BFBFFF"
            }
            "Blue" -> {
                activeColor = "#4CA6FF"
                inactiveColor = "#264CA6FF"
                textColor = "#B3D9FF"
            }
            "Peacock Green" -> {
                activeColor = "#52CCCC"
                inactiveColor = "#2652CCCC"
                textColor = "#A1E6E6"
            }
            "Green" -> {
                activeColor = "#3EA87A"
                inactiveColor = "#263EA87A"
                textColor = "#B1E6CF"
            }
            "Gray" -> {
                activeColor = "#7B8399"
                inactiveColor = "#267B8399"
                textColor = "#CCDAFF"
            }
        }
    }

    fun getActive() : String = activeColor
    fun getInactive() : String = inactiveColor
    fun getText() : String = textColor
}