package com.piotr.practicepad.views.addExerciseSet

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.AddExerciseDetailCardBinding
import com.piotr.practicepad.extensions.bind
import com.piotr.practicepad.utils.BindableRecyclerViewAdapter
import com.piotr.practicepad.views.exercise.Exercise

class AddExerciseSetAdapter :
    BindableRecyclerViewAdapter<RecyclerView.ViewHolder, Exercise>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder =
        RowViewHolder(parent.bind(R.layout.add_exercise_detail_card, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? RowViewHolder)?.bindData(item = items[position])
    }

    inner class RowViewHolder(private val binding: AddExerciseDetailCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Exercise) {
            binding.model = item
            binding.executePendingBindings()
        }
    }

}
