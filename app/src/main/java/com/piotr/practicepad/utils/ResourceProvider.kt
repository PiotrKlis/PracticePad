package com.piotr.practicepad.utils

import android.content.Context
import javax.inject.Inject


interface ResourceProvider {
    fun getImageForString(string: String): Int
    fun getStringFromImage(image: Int): String
}

class AndroidResourceProvider @Inject constructor(private val context: Context) : ResourceProvider {
    override fun getImageForString(string: String): Int {
        context.apply {
            return resources.getIdentifier(
                "@drawable/$string",
                null,
                packageName
            )
        }
    }

    override fun getStringFromImage(image: Int): String =
        context.resources.getResourceEntryName(image);
}
