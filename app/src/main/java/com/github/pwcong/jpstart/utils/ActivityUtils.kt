package com.github.pwcong.jpstart.utils

import android.content.Intent
import android.net.Uri
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.manager.ActivityManager

class ActivityUtils private constructor() {
    init {
        throw RuntimeException("(￣y▽￣)╭ Ohohoho.....")
    }

    companion object {
        fun share(message: String?) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            ActivityManager.getInstance().getCurrent()?.let {
                Intent.createChooser(
                    intent,
                    ResourceUtils.getString(it, R.string.share_to)
                )
            }
        }

        fun openUrl(url: String?) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))

            ActivityManager.getInstance().getCurrent()?.startActivity(intent)
        }
    }
}
