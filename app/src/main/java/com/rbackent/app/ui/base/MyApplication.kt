package com.rbackent.app.ui.base

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.widget.ProgressBar
import com.rbackent.app.R
import java.util.*

class MyApplication : Application() {
    companion object {
        var mContext: MyApplication? = null
        fun getContext(): MyApplication {
            if (mContext == null)
                mContext = MyApplication()
            return mContext as MyApplication
        }
        var mLoadingView: ProgressBar? = null
        var mLoader: Dialog? = null

        fun showLoader(context: Context) {
            try {
                if (mLoader != null) {
                    mLoader!!.dismiss()
                    mLoader!!.cancel()
                }
                if (mLoadingView != null) {
                    //   mLoadingView!!.hide()
                }
                mLoader = Dialog(context)
                mLoader!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                mLoader!!.setContentView(R.layout.layout_progress_dialog)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(mLoader!!.window)
                        ?.setBackgroundDrawable(
                            ColorDrawable(
                                Color.TRANSPARENT
                            )
                        )
                }
                mLoader!!.setCancelable(false)
                mLoadingView = mLoader!!.findViewById(R.id.loadingView)
                //mLoadingView!!.show()
                mLoader!!.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun hideLoader() {
            if (mLoader != null) {
                mLoader!!.dismiss()
                mLoader!!.cancel()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext as MyApplication
    }
}