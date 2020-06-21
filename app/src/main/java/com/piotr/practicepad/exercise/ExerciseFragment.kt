package com.piotr.practicepad.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExcerciseBinding
import com.piotr.practicepad.ui.main.utils.observeEvent
import com.piotr.practicepad.utils.BaseFragment
import kotlinx.coroutines.launch


class ExerciseFragment : BaseFragment() {
    private val viewModel: ExerciseViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding(inflater, container).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.renderActiveExerciseSet()
        viewModel.exerciseTimer.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseEvent.NextExercise -> viewModel.renderNextExercise(event.position)
            }
        }
        viewModel.exerciseSetTimer.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseEvent.SetEnded -> viewModel.setEnded()
            }
        }
    }

    override fun onPause() {
        viewModel.onPause()
        super.onPause()
    }

    private fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExcerciseBinding {
        val binding: FragmentExcerciseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_excercise, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            exerciseSetTimer = viewModel.exerciseSetTimer
            exerciseTimer = viewModel.exerciseTimer
            practice = viewModel.practiceState
        }
        return binding
    }
}
