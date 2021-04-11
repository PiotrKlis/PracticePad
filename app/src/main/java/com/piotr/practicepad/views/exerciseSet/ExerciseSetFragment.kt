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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseSetBinding
import com.piotr.practicepad.utils.BaseFragment
import com.piotr.practicepad.views.addExercise.AddExerciseFragmentArgs
import kotlin.time.ExperimentalTime

@ExperimentalTime
class ExerciseSetFragment : BaseFragment(), Editor {
    private val viewModel: ExerciseSetViewModel by viewModels { viewModelFactory }
    private val adapter = ExerciseSetAdapter(this, ::updateTime)
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
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.renderData(args.exerciseSetId)
        binding.recyclerList.adapter = adapter
        binding.fabAddExercise.setOnClickListener { findNavController()
                    .navigate(
                        R.id.action_exerciseSetDetailFragment_to_addExerciseFragment,
                        AddExerciseFragmentArgs(args.exerciseSetId).toBundle()
                    )
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
}
