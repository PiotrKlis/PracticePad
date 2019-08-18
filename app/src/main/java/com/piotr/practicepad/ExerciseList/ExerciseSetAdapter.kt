package com.piotr.practicepad.ui.main.ExerciseList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.data.db.SharedPrefs


import kotlinx.android.synthetic.main.exercise_set_row.view.*

class ExerciseSetAdapter : RecyclerView.Adapter<ExerciseSetAdapter.ViewHolder>() {

    private var onClickListener: CheckBoxListener? = null
    private var exerciseSetList: List<ExerciseSet> = arrayListOf()

    fun setListener(listener: CheckBoxListener) {
        onClickListener = listener
    }

    fun setItems(items: List<ExerciseSet>?) {
        this.exerciseSetList = items!!
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_set_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exerciseSetList.get(position)
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return exerciseSetList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.item_number
        val content: TextView = view.content
        val checkBox: CheckBox = view.checkbox

        fun setData(item: ExerciseSet) {
            number.text = item.id.toString()
            content.text = item.title
            checkBox.isChecked = shouldBeChecked(adapterPosition)
            checkBox.setOnClickListener {
                SharedPrefs.setActiveSet(adapterPosition)
                onClickListener?.checkboxClick() }
        }
    }

    fun shouldBeChecked(position: Int): Boolean {
        return position == SharedPrefs.getActiveSet()
    }
}
