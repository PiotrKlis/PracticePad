package com.piotr.practicepad.ui.main.ExerciseList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.SharedPrefs


import kotlinx.android.synthetic.main.fragment_exerciseset.view.*

class ExerciseSetAdapter : RecyclerView.Adapter<ExerciseSetAdapter.ViewHolder>() {

    private var onClickListener: ExerciseListCheckBoxListener? = null
    private var exerciseSetList: Array<ExerciseSetData> = emptyArray()
    private var selectedExerciseSet: Int = 0

    fun setListener(listener: ExerciseListCheckBoxListener) {
        onClickListener = listener
    }

    fun setItems(items: Array<ExerciseSetData>?, selectedExerciseSet: Int?) {
        this.exerciseSetList = items!!
        this.selectedExerciseSet = selectedExerciseSet!!
        notifyDataSetChanged()
    }

    fun setSelectedExerciseSet(selectedSet: Int?) {
        this.selectedExerciseSet = selectedSet!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_exerciseset, parent, false)
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

        fun setData(item: ExerciseSetData) {
            number.text = item.id.toString()
            content.text = item.name
            checkBox.isChecked = shouldBeChecked(adapterPosition)
            checkBox.setOnClickListener {
                SharedPrefs.setActiveSet(adapterPosition)
                onClickListener?.checkboxClick() }
        }

        override fun toString(): String {
            return super.toString() + " '" + content.text + "'"
        }
    }

    fun shouldBeChecked(position: Int): Boolean {
        return position == selectedExerciseSet
    }
}
