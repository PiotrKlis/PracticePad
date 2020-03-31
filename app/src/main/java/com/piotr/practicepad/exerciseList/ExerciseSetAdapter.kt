package com.piotr.practicepad.exerciseList


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.piotr.practicepad.R
import kotlinx.android.synthetic.main.exercise_set_row.view.*

class ExerciseSetAdapter(
    private val checkboxClick: (Int) -> Unit,
    private val shouldCheckboxBeChecked: (Int) -> Boolean
) : RecyclerView.Adapter<ExerciseSetAdapter.ViewHolder>() {
    private var exerciseSetList: List<ExerciseSet> = arrayListOf()

    fun setItems(items: List<ExerciseSet>) {
        this.exerciseSetList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_set_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exerciseSetList[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return exerciseSetList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val content: TextView = view.content
        private val checkBox: CheckBox = view.checkbox

        fun setData(item: ExerciseSet) {
            content.text = item.name
            Log.d("AAA", item.id.toString())
            checkBox.isChecked = shouldCheckboxBeChecked(item.id)
            checkBox.setOnClickListener {
                checkboxClick.invoke(item.id)
            }
        }
    }
}
