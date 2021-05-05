package com.piotr.practicepad.views.exerciseSet


import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.ikovac.timepickerwithseconds.MyTimePickerDialog
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.EditExerciseCardBinding
import com.piotr.practicepad.extensions.bind
import com.piotr.practicepad.extensions.minutesToMillis
import com.piotr.practicepad.extensions.secondsToMilliseconds
import com.piotr.practicepad.utils.BindableRecyclerViewAdapter
import com.piotr.practicepad.utils.DurationPicker
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
                val test = DurationPicker(
                    binding.time.context,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute -> },
                    0,
                    30
                )
                test.setButton(
                    BUTTON_POSITIVE,
                    "OK",
                    { dialog, which -> })
                test.show()
            }
//                binding.time.addTextChangedListener { text ->
//                    validateInput(
//                        text.toString(),
//                        binding.time.context,
//                        item.id
//                    )
//                }

            binding.executePendingBindings()
        }

        private fun validateInput(text: String, context: Context, id: Int) {
            if (text.contains(":")) {
                val index = text.indexOf(":")
                try {
                    val minutes =
                        StringBuilder().append(text[index - 2], text[index - 1]).toString().toLong()
                            .minutesToMillis()
                    val seconds =
                        StringBuilder().append(text[index + 1], text[index + 2]).toString().toLong()
                            .secondsToMilliseconds()
                    val result = minutes + seconds
                    updateTime(result, id)
                } catch (e: Exception) {
                    Toast.makeText(context, "Enter valid time format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Enter valid time format", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
