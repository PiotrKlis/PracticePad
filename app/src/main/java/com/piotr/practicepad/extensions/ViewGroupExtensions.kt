package com.piotr.practicepad.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <T : ViewDataBinding> ViewGroup.bind(layoutId: Int, attachToParent: Boolean = true): T {
    return DataBindingUtil.inflate(getLayoutInflater(), layoutId, this, attachToParent)
}

val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(this.context)