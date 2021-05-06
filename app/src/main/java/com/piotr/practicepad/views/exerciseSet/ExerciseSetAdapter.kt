package com.piotr.practicepad.views.exerciseSet


import DurationPickerDialog
import android.app.TimePickerDialog
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.EditExerciseCardBinding
import com.piotr.practicepad.extensions.*
import com.piotr.practicepad.utils.BindableRecyclerViewAdapter
import com.piotr.practicepad.views.exercise.Exercise

class ExerciseSetAdapter(
    private val editor: Editor,
    private val updateTime: (time: Long, id: Int) -> Unit
) :
    BindableRecyclerViewAdapter<RecyclerView.ViewHolder, Exercise>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RowViewHolder(
            binding = parent.bind(R.layout.edit_exercise_card, false),
            updateTime = updateTime
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? RowViewHolder)?.bindData(
            item = items[position],
            adapterParams = AdapterParams(position, items.size),
            editor = editor
        )
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    private class RowViewHolder(
        private val binding: EditExerciseCardBinding,
        private val updateTime: (time: Long, id: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            item: Exercise,
            adapterParams: AdapterParams,
            editor: Editor
        ) {
            binding.model = item
            binding.adapterParams = adapterParams
            binding.editor = editor
            binding.time.setOnClickListener {
                DurationPickerDialog(
                    binding.time.context,
                    TimePickerDialog.OnTimeSetListener { view, minute, second ->
                        updateTime(minute.minutesToMillis() + second.secondsToMillis(), item.id)
                    },
                    item.time.millisToMinutes(),
                    item.time.millisToSeconds()
                ).show()
            }
            binding.executePendingBindings()
        }
    }
}
