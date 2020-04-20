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
import com.piotr.practicepad.metronome.Metronome
import com.piotr.practicepad.timers.ExerciseSetTimer
import com.piotr.practicepad.timers.ExerciseTimer
import com.piotr.practicepad.ui.main.utils.observeEvent
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ExerciseFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var _exerciseSetTimer: ExerciseSetTimer

    @Inject
    lateinit var _exerciseTimer: ExerciseTimer

    @Inject
    lateinit var metronome: Metronome

    private lateinit var exerciseViewModel: ExerciseViewModel
    private val practiceState = PracticeState()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        exerciseViewModel = viewModelProvider(viewModelFactory)
        return getBinding(inflater, container).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseViewModel.renderExerciseSet()
        exerciseViewModel.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseEvent.PowerClick -> handleClick(event)
                is ExerciseEvent.OnPause -> handleOnPause()
            }
        }
        _exerciseTimer.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseEvent.NextExercise -> handleNextExercise(event.position)
            }
        }
    }

    override fun onPause() {
        exerciseViewModel.onPause()
        super.onPause()
    }

    private fun handleNextExercise(position: Int) {
        _exerciseTimer.startNextExercise(position)
        exerciseViewModel.renderNextExercise(position)
    }

    private fun handleOnPause() {
        _exerciseTimer.onPause()
        _exerciseSetTimer.onPause()
        metronome.onPause()
        practiceState.onPause()
    }

    private fun handleClick(event: ExerciseEvent.PowerClick) {
        _exerciseTimer.handleClick(event.state)
        _exerciseSetTimer.handleClick(event.state)
        metronome.handleClick(event.state)
        practiceState.handleClick(event.state)
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
            exerciseSetTimer = _exerciseSetTimer
            exerciseTimer = _exerciseTimer
            practice = practiceState
        }
        return binding
    }
}
