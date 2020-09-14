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
        (holder as? RowViewHolder)?.bindData(
            item = items[position],
            adapterParams = AdapterParams(position, items.size),
            editor = editor
        )
    }

    private class RowViewHolder(private val binding: ExerciseDetailCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Exercise, adapterParams: AdapterParams, editor: Editor) {
            binding.model = item
            binding.adapterParams = adapterParams
            binding.editor = editor
            binding.executePendingBindings()
            binding.time.setOnClickListener {

            }
        }
    }
}
