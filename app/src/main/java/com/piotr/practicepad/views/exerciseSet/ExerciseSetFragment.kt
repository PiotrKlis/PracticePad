package com.piotr.practicepad.views.exerciseSet

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseSetBinding
import com.piotr.practicepad.utils.BaseFragment
import com.piotr.practicepad.views.addExercise.AddExerciseFragmentArgs
import kotlin.time.ExperimentalTime

@ExperimentalTime
class ExerciseSetFragment : BaseFragment(), Editor, ExerciseSetEditor {
    private val viewModel: ExerciseSetViewModel by viewModels { viewModelFactory }
    private val adapter = ExerciseSetAdapter(this, ::updateTime).apply { setHasStableIds(true) }
    private val args: ExerciseSetFragmentArgs by navArgs()
    private lateinit var binding: FragmentExerciseSetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise_set,
            container,
            false
        )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
            editor = this@ExerciseSetFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerList.adapter = adapter
        setState()
        viewModel.state.observe(viewLifecycleOwner, Observer { setViewElements() })
    }

    private fun setState() {
        if (args.exerciseSetId == DEFAULT_VALUE) {
            viewModel.createNewSet()
        } else {
            viewModel.setData(args.exerciseSetId)
        }
    }

    private fun setViewElements() {
        binding.fabAddExercise.setOnClickListener {
            viewModel.state.value?.let { state ->
                findNavController()
                    .navigate(
                        R.id.action_exerciseSetDetailFragment_to_addExerciseFragment,
                        AddExerciseFragmentArgs(state.id).toBundle()
                    )
            }
        }
        binding.name.addTextChangedListener { text -> viewModel.updateName(text.toString()) }
        binding.tempo.addTextChangedListener { text -> validateTempo(text) }
    }

    override fun delete(position: Int) {
//        binding.recyclerList.adapter?.notifyItemRemoved(position)
        viewModel.delete(position)
        binding.recyclerList.adapter?.notifyDataSetChanged()
    }

    override fun moveUp(position: Int) {
        viewModel.moveUp(position)
//        binding.recyclerList.adapter?.notifyItemMoved(position, position - 1)
//        binding.recyclerList.adapter?.notifyItemMoved(position -1, position)
        binding.recyclerList.adapter?.notifyDataSetChanged()
        Log.d("AAA move up", "$position to ${position - 1}")
    }

    override fun moveDown(position: Int) {
        viewModel.moveDown(position)
//        binding.recyclerList.adapter?.notifyItemMoved(position, position + 1)
//        binding.recyclerList.adapter?.notifyItemMoved(position + 1, position)
        binding.recyclerList.adapter?.notifyDataSetChanged()
        Log.d("AAA move down", "$position to ${position + 1}")
    }

    private fun validateTempo(text: Editable?) {
        try {
            val tempo = text.toString().toInt()
            if (tempo in 40 until 221) {
                viewModel.updateTempo(tempo)
            } else {
                showTempoError()
            }
        } catch (exception: Exception) {
            showTempoError()
        }
    }

    private fun updateTime(time: Long, exerciseId: Int) {
        viewModel.updateTime(time, exerciseId)
    }

    private fun showTempoError() {
        Toast.makeText(context, "Tempo must be between 40-221 BPM", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val DEFAULT_VALUE = -1
    }

    override fun delete() {
        viewModel.deleteExerciseSet()
        activity?.onBackPressed()
    }
}
