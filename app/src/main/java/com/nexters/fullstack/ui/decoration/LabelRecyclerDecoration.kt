package com.nexters.fullstack.ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.ext.toPx

class LabelRecyclerDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = SPACING_VERTICAL.toPx
        outRect.top = SPACING_VERTICAL.toPx
    }

    companion object {
        private const val SPACING_VERTICAL = 10
    }
}