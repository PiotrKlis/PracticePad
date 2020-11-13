package com.piotr.practicepad.data.repository

import android.content.Context
import javax.inject.Inject


interface ResourceProvider {
    fun getImageForString(string: String): Int
}

class AndroidResourceProvider @Inject constructor(private val context: Context) :
    ResourceProvider {

    companion object {
        fun init(context: Context) {
            AndroidResourceProvider(context)
        }
    }

    override fun getImageForString(string: String): Int {
        context.apply {
            return resources.getIdentifier(
                "@drawable/$string",
                null,
                packageName
            )
        }
    }
}
