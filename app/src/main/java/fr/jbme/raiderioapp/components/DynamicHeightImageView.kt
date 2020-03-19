package fr.jbme.raiderioapp.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import fr.jbme.raiderioapp.R


open class DynamicHeightImageView : AppCompatImageView {
    private var mHeightRatio = 0f

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val ta: TypedArray = context.theme
            .obtainStyledAttributes(attrs, R.styleable.DynamicHeightImageView, 0, 0)
        mHeightRatio = try {
            ta.getFloat(R.styleable.DynamicHeightImageView_height_ratio, 0.0f)
        } finally {
            ta.recycle()
        }
    }

    constructor(context: Context?) : super(context)

    fun setHeightRatio(ratio: Float) {
        if (ratio != mHeightRatio) {
            mHeightRatio = ratio
            requestLayout()
        }
    }

    val heightRatio: Double
        get() = mHeightRatio.toDouble()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mHeightRatio > 0.0f) {
            // set the image views size
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height = (width * mHeightRatio).toInt()
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}