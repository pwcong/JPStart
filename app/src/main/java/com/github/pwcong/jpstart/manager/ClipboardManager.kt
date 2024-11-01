package com.github.pwcong.jpstart.manager

import android.content.ClipData
import android.content.Context
import com.github.pwcong.jpstart.App

class ClipboardManager private constructor() {
    private val clipboardManager = App.getInstance()
        .getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager

    val text: String
        get() = clipboardManager.primaryClip!!.getItemAt(0).text.toString()

    fun setText(label: String?, text: String?) {
        clipboardManager.setPrimaryClip(ClipData.newPlainText(label, text))
    }

    companion object {
        private var instance: ClipboardManager? = null

        @Synchronized
        fun getInstance(): ClipboardManager {
            if (instance == null) {
                instance = ClipboardManager()
            }

            return instance!!
        }
    }
}
