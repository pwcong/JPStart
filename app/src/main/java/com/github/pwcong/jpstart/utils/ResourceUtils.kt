package com.github.pwcong.jpstart.utils

import android.content.Context

class ResourceUtils private constructor() {
    init {
        throw RuntimeException("♪(^∇^*)")
    }

    companion object {
        fun getString(context: Context, resId: Int): String {
            return context.resources.getString(resId)
        }

        fun getDimension(context: Context, resId: Int): Float {
            return context.resources.getDimension(resId)
        }
    }
}
