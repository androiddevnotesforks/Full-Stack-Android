package com.nexters.fullstack.ui.widget.bottomsheet.recyclerview

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nexters.fullstack.R
import com.nexters.fullstack.ext.toPx

class GridLayoutRecyclerOnScrollListener(
    private val toolbar: CollapsingToolbarLayout,
    private val frameLayout: FrameLayout,
    private val title: TextView
) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as? GridLayoutManager

        val visibleItemPosition = layoutManager?.findFirstCompletelyVisibleItemPosition() ?: 999

        if (visibleItemPosition != FIRST_POSITION && visibleItemPosition % 2 == 0) {
            toolbar.background.alpha = 0
            title.gravity = Gravity.CENTER
            title.textSize = 4.toPx.toFloat()
            frameLayout.visibility = View.GONE
        } else if (recyclerView.canScrollVertically(-1)) {
            toolbar.setBackgroundColor(
                ResourcesCompat.getColor(
                    recyclerView.resources,
                    R.color.background,
                    null
                )
            )
            toolbar.alpha = 1f
            title.gravity = Gravity.START
            title.textSize = 7.toPx.toFloat()
            frameLayout.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val FIRST_POSITION = 0
    }
}