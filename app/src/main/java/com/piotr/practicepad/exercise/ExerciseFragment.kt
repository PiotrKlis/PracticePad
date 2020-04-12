package com.piotr.practicepad.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExcerciseBinding
import com.piotr.practicepad.extensions.viewModelProvider
import com.piotr.practicepad.timers.ExerciseSetTimerViewModel
import com.piotr.practicepad.ui.main.utils.observeEvent
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ExerciseFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var exerciseSetTimerViewModel: ExerciseSetTimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        exerciseViewModel = viewModelProvider(viewModelFactory)
        exerciseSetTimerViewModel = viewModelProvider(viewModelFactory)
        return getBinding(inflater, container).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseViewModel.renderExerciseSet()
        exerciseViewModel.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseEvent.PowerClick -> exerciseSetTimerViewModel.handleClick(event.state)
            }
        }
    }

    override fun onPause() {
//        exerciseSetTimerViewModel.timer.onFinish()
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
            viewmodel = exerciseViewModel
        }
        return binding
    }
}
