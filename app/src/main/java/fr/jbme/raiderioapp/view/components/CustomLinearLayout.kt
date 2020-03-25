package fr.jbme.raiderioapp.view.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target


class CustomLinearLayout : LinearLayout, Target {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
        background = BitmapDrawable(resources, bitmap)
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        background = placeHolderDrawable ?: return
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        background = errorDrawable ?: return
    }
}