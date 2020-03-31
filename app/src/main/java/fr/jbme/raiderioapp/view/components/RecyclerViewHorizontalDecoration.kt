package fr.jbme.raiderioapp.view.components

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHorizontalDecoration(
    private val marginLeft: Int?,
    private val marginTop: Int?,
    private val marginRight: Int?,
    private val marginBottom: Int?,
    private val columns: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        outRect.right = marginRight ?: 0
        outRect.bottom = marginBottom ?: 0
        if (position < columns) {
            outRect.left = marginLeft ?: 0
        }
        if (position % columns == 0) {
            outRect.top = marginTop ?: 0
        }
    }

}