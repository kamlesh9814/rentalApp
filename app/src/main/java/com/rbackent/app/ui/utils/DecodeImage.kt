package com.labelprinter.apps.utils

import android.graphics.Bitmap

object DecodeImage {

    fun decodeFile(path: String, DESIREDWIDTH: Int, DESIREDHEIGHT: Int): String? {
        val strMyImagePath: String? = null
        var scaledBitmap: Bitmap

        try {
            // Part 1: Decode image
            val unscaledBitmap =
                ScalingUtilities.decodeFile(path, DESIREDWIDTH, DESIREDHEIGHT, ScalingUtilities.ScalingLogic.FIT)

            if (!(unscaledBitmap.getWidth() <= DESIREDWIDTH && unscaledBitmap.getHeight() <= DESIREDHEIGHT)) {
                // Part 2: Scale image
                scaledBitmap = ScalingUtilities.createScaledBitmap(
                    unscaledBitmap,
                    DESIREDWIDTH,
                    DESIREDHEIGHT,
                    ScalingUtilities.ScalingLogic.FIT
                )
            } else {
                unscaledBitmap.recycle()
                return path
            }

            scaledBitmap.recycle()
        } catch (e: Throwable) {
        }

        return strMyImagePath ?: path
    }
}