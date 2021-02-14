package com.piotr.practicepad.views.addExerciseSet

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.ExerciseDetailCardBinding
import com.piotr.practicepad.extensions.bind
import com.piotr.practicepad.utils.BindableRecyclerViewAdapter
import com.piotr.practicepad.views.exercise.Exercise

class AddExerciseSetAdapter :
    BindableRecyclerViewAdapter<RecyclerView.ViewHolder, Exercise>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RowViewHolder(parent.bind(R.layout.edit_exercise_detail_card, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? RowViewHolder)?.bindData(
            item = items[position]
        )
    }

    private class RowViewHolder(private val binding: ExerciseDetailCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Exercise) {
            binding.model = item
            binding.executePendingBindings()
        }
    }

}
