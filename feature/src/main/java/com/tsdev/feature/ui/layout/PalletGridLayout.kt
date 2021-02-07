package com.tsdev.feature.ui.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import com.tsdev.feature.R
import com.tsdev.feature.ui.data.PalletItem

class PalletGridLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defaultAttr: Int = 0
) : GridLayout(context, attrs, defaultAttr) {

    private val _items = mutableListOf<PalletItem>()

    var setItems: List<PalletItem>
        set(value) {
            if (value.isNotEmpty()) {
                _items.addAll(value)
            }
        }
        get() = _items

    //이것도 따로 data class 를 만드는게 좋을지 ?

    private val PADDING_START = 10
    private val PADDING_END = 10

    init {
        columnCount = 2
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        rootView.setPadding(PADDING_START, 0, PADDING_END, 0)
    }

    private fun setOnInitView() {
        _items.forEach { item ->
            val childView =
                LayoutInflater.from(context).inflate(R.layout.item_pallet_view, this, false)

            val textView = childView.findViewById<TextView>(R.id.label)

            textView.width = (this.measuredWidth / 2) - textView.marginLeft - textView.marginRight

            textView.run {
                text = item.name
                setBackgroundColor(Color.parseColor(item.hexColor))
            }

            addView(childView)
        }
    }


    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        Log.e("onMeasure", "Called")
//        if (childCount > 0) {
        setOnInitView()
//        }
    }
}