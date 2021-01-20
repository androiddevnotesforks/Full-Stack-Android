package com.nexters.fullstack.widget

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ViewProperty<T>(private var defaultValue: T): ReadWriteProperty<CustomDialog.Builder, T> {
    override fun getValue(thisRef: CustomDialog.Builder, property: KProperty<*>): T {
        return defaultValue
    }

    override fun setValue(thisRef: CustomDialog.Builder, property: KProperty<*>, value: T) {
        defaultValue = value
    }
}