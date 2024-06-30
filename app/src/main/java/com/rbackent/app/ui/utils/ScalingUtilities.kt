package com.labelprinter.apps.utils

import android.graphics.*

object ScalingUtilities {

    fun decodeFile(
        path: String, dstWidth: Int, dstHeight: Int,
        scalingLogic: ScalingLogic
    ): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        options.inJustDecodeBounds = false
        options.inSampleSize = calculateSampleSize(
            options.outWidth, options.outHeight, dstWidth,
            dstHeight, scalingLogic
        )

        return BitmapFactory.decodeFile(path, options)
    }


    fun createScaledBitmap(
        unscaledBitmap: Bitmap, dstWidth: Int, dstHeight: Int,
        scalingLogic: ScalingLogic
    ): Bitmap {
        val srcRect = calculateSrcRect(
            unscaledBitmap.width, unscaledBitmap.height,
            dstWidth, dstHeight, scalingLogic
        )
        val dstRect = calculateDstRect(
            unscaledBitmap.width, unscaledBitmap.height,
            dstWidth, dstHeight, scalingLogic
        )
        val scaledBitmap = Bitmap.createBitmap(
            dstRect.width(), dstRect.height(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(scaledBitmap)
        canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, Paint(Paint.FILTER_BITMAP_FLAG))

        return scaledBitmap
    }


    enum class ScalingLogic {
        CROP, FIT
    }

    fun calculateSampleSize(
        srcWidth: Int, srcHeight: Int, dstWidth: Int, dstHeight: Int,
        scalingLogic: ScalingLogic
    ): Int {
        if (scalingLogic == ScalingLogic.FIT) {
            val srcAspect = srcWidth.toFloat() / srcHeight.toFloat()
            val dstAspect = dstWidth.toFloat() / dstHeight.toFloat()

            return if (srcAspect > dstAspect) {
                srcWidth / dstWidth
            } else {
                srcHeight / dstHeight
            }
        } else {
            val srcAspect = srcWidth.toFloat() / srcHeight.toFloat()
            val dstAspect = dstWidth.toFloat() / dstHeight.toFloat()

            return if (srcAspect > dstAspect) {
                srcHeight / dstHeight
            } else {
                srcWidth / dstWidth
            }
        }
    }

    fun calculateSrcRect(
        srcWidth: Int, srcHeight: Int, dstWidth: Int, dstHeight: Int,
        scalingLogic: ScalingLogic
    ): Rect {
        if (scalingLogic == ScalingLogic.CROP) {
            val srcAspect = srcWidth.toFloat() / srcHeight.toFloat()
            val dstAspect = dstWidth.toFloat() / dstHeight.toFloat()

            if (srcAspect > dstAspect) {
                val srcRectWidth = (srcHeight * dstAspect).toInt()
                val srcRectLeft = (srcWidth - srcRectWidth) / 2
                return Rect(srcRectLeft, 0, srcRectLeft + srcRectWidth, srcHeight)
            } else {
                val srcRectHeight = (srcWidth / dstAspect).toInt()
                val scrRectTop = (srcHeight - srcRectHeight) / 2
                return Rect(0, scrRectTop, srcWidth, scrRectTop + srcRectHeight)
            }
        } else {
            return Rect(0, 0, srcWidth, srcHeight)
        }
    }

    fun calculateDstRect(
        srcWidth: Int, srcHeight: Int, dstWidth: Int, dstHeight: Int,
        scalingLogic: ScalingLogic
    ): Rect {
        if (scalingLogic == ScalingLogic.FIT) {
            val srcAspect = srcWidth.toFloat() / srcHeight.toFloat()
            val dstAspect = dstWidth.toFloat() / dstHeight.toFloat()

            return if (srcAspect > dstAspect) {
                Rect(0, 0, dstWidth, (dstWidth / srcAspect).toInt())
            } else {
                Rect(0, 0, (dstHeight * srcAspect).toInt(), dstHeight)
            }
        } else {
            return Rect(0, 0, dstWidth, dstHeight)
        }
    }
}