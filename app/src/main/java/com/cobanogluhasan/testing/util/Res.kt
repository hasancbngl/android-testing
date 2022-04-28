package com.cobanogluhasan.testing.util

import android.content.res.Resources
import android.graphics.Color
import android.os.Build

class Res(original: Resources) :
    Resources(original.assets, original.displayMetrics, original.configuration) {
    @Throws(NotFoundException::class)
    override fun getColor(id: Int): Int {
        return getColor(id, null)
    }

    @Throws(NotFoundException::class)
    override fun getColor(id: Int, theme: Theme?): Int {
        return when (getResourceEntryName(id)) {
            "black" ->
                // You can change the return value to an instance field that loads from SharedPreferences.
                Color.GREEN // used as an example. Change as needed.
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    super.getColor(id, theme)
                } else super.getColor(id)
            }
        }
    }
}