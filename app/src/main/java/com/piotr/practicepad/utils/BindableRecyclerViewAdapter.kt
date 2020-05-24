package com.piotr.practicepad.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BindableRecyclerViewAdapter<VH : RecyclerView.ViewHolder, T> : RecyclerView.Adapter<VH>() {

    var items: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun <VH : RecyclerView.ViewHolder, T> RecyclerView.adapter(oldList: MutableList<T>?, newList: MutableList<T>?) {
    if (adapter !is BindableRecyclerViewAdapter<*, *>) {
        throw IllegalStateException("Adapter needs to inherit from BindableRecyclerViewAdapter")
    }
    val bindableAdapter = this.adapter as? BindableRecyclerViewAdapter<VH, T>
    if (newList == null) return
    if (oldList != newList) {
        bindableAdapter?.items = newList
    }
}