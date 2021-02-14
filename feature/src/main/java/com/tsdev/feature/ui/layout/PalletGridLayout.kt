package com.tsdev.feature.ui.layout

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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

    lateinit var setOnLabelClickListener: (PalletItem) -> Unit

    var setItems: List<PalletItem>
        set(value) {
            if (value.isNotEmpty()) {
                _items.addAll(value)
            }
        }
        get() = _items

    lateinit var selectedView: View

    lateinit var selectedPalletItem: PalletItem

    init {
        columnCount = 2
    }

    fun setOnInitView() {
        _items.forEachIndexed { index, item ->
            val childView =
                LayoutInflater.from(context).inflate(R.layout.item_pallet_view, this, false)

            val textView = childView.findViewById<TextView>(R.id.label)

            if (index % 2 == 0) {
                textView.width =
                    (resources.displayMetrics.widthPixels / 2) - textView.marginRight - (textView.marginLeft * 2)
            } else {
                textView.width =
                    (resources.displayMetrics.widthPixels / 2) - textView.marginRight - textView.marginLeft
            }


            textView.run {
                text = item.name

                setBackgroundColor(Color.parseColor(item.backgroundColor))
                setTextColor(Color.parseColor(item.textColor))
                setOnClickListener { itemView ->
                    if (::selectedView.isInitialized) {
                        selectedView.isSelected = false
                        selectedView.setBackgroundColor(Color.parseColor(selectedPalletItem.backgroundColor))
                    }

                    selectedPalletItem = item
                    selectedView = itemView


                    itemView.isSelected = true

                    setBackgroundColor(Color.parseColor(item.selectedBackgroundColor))
                    setOnLabelClickListener(item)

                    invalidate()
                }
            }

            addView(childView)
        }
    }
}