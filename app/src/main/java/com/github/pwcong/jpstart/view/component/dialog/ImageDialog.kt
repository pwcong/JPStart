package com.github.pwcong.jpstart.ui.component.dialog

import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.github.pwcong.jpstart.R

class ImageDialog private constructor(context: Context, themeResId: Int) :
    Dialog(context, themeResId) {
    class Builder(var context: Context) {
        private var resId: Int = 0
        var width: Int = 0
        var height: Int = 0

        fun setResId(resId: Int): Builder {
            this.resId = resId
            return this
        }

        fun override(width: Int, height: Int): Builder {
            this.width = width
            this.height = height
            return this
        }

        fun create(): ImageDialog {
            val dialog = ImageDialog(context, R.style.AppTheme_Dialog_NoTitle)
            val imageView = ImageView(context)

            val requestOptions = RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            Glide.with(context).asGif().load(resId).apply(requestOptions).into(imageView)

            dialog.addContentView(imageView, LinearLayout.LayoutParams(width, height))

            return dialog
        }
    }
}
