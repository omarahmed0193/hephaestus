package com.afterapps.hephaestus.ui.home

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.afterapps.hephaestus.R
import kotlin.math.roundToInt

class ArtCardView(context: Context, attrs: AttributeSet) : CardView(context, attrs) {

    var artRatio: Float = 0f

    var calculateHeight: Boolean = false

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ArtCardView,
            0, 0
        ).apply {

            try {
                artRatio = getFloat(R.styleable.ArtCardView_artRatio, 0f)

                calculateHeight = getBoolean(R.styleable.ArtCardView_calculateHeight, false)
            } finally {
                recycle()
            }
        }
    }

    // Calculate card width/height according to image aspect ratio to avoid StaggeredLayout rearranging items
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        when (calculateHeight) {
            true -> super.onMeasure(widthMeasureSpec, calculateMeasureSpec(width, 1 / artRatio))
            else -> super.onMeasure(calculateMeasureSpec(height, artRatio), heightMeasureSpec)
        }
    }

    private fun calculateMeasureSpec(otherDimension: Int, ratio: Float) =
        MeasureSpec.makeMeasureSpec((otherDimension * ratio).roundToInt(), MeasureSpec.EXACTLY)
}