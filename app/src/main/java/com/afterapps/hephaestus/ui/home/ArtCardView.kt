package com.afterapps.hephaestus.ui.home

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.afterapps.hephaestus.R
import kotlin.math.roundToInt

class ArtCardView(context: Context, attrs: AttributeSet) : CardView(context, attrs) {

    var artRatio: Float = 0f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ArtCardView,
            0, 0
        ).apply {

            try {
                artRatio = getFloat(R.styleable.ArtCardView_artRatio, 0f)
            } finally {
                recycle()
            }
        }
    }

    // Calculate card width according to image aspect ratio to avoid StaggeredLayout rearranging items
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val artWidthMeasureSpec =
            MeasureSpec.makeMeasureSpec((height * artRatio).roundToInt(), MeasureSpec.EXACTLY)
        super.onMeasure(artWidthMeasureSpec, heightMeasureSpec)
    }
}