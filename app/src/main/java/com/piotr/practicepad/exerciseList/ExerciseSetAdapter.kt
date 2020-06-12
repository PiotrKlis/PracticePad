package com.piotr.practicepad.exerciseList


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.ExerciseSetRowBinding
import com.piotr.practicepad.extensions.bind
import com.piotr.practicepad.utils.BindableRecyclerViewAdapter

class ExerciseSetAdapter(
    private val checkBoxHandler: CheckBoxHandler,
    private val navigationHandler: NavigationHandler
) : BindableRecyclerViewAdapter<RecyclerView.ViewHolder, ExerciseSetEntity>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder =
        RowViewHolder(parent.bind(R.layout.exercise_set_row, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? RowViewHolder)?.bindData(items[position], checkBoxHandler, navigationHandler)
    }

    override fun getItemCount(): Int = items.size

    inner class RowViewHolder(private val binding: ExerciseSetRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            item: ExerciseSetEntity,
            checkBoxHandler: CheckBoxHandler,
            navigationHandler: NavigationHandler
        ) {
            binding.model = item
            binding.checkBoxHandler = checkBoxHandler
            binding.navigationHandler = navigationHandler
            binding.executePendingBindings()
        }
    }
}
