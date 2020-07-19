package com.piotr.practicepad.exerciseSetDetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.ExerciseDetailCardBinding
import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.extensions.bind
import com.piotr.practicepad.utils.BindableRecyclerViewAdapter

class ExerciseSetDetailsAdapter(private val editor: Editor) :
    BindableRecyclerViewAdapter<RecyclerView.ViewHolder, Exercise>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RowViewHolder(parent.bind(R.layout.exercise_detail_card, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ExerciseSetDetailsAdapter.RowViewHolder)?.bindData(
            item = items[position],
            position = position,
            editor = editor
        )
    }

    inner class RowViewHolder(private val binding: ExerciseDetailCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Exercise, position: Int, editor: Editor) {
            binding.model = item
            binding.position = position
            binding.editor = editor
            binding.executePendingBindings()
        }
    }
}
