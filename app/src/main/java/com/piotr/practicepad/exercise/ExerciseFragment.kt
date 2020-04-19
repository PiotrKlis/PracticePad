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
    private lateinit var exerciseViewModel: ExerciseViewModel
    @Inject
    lateinit var exerciseSetTimer: ExerciseSetTimer
    @Inject
    lateinit var exerciseTimer: ExerciseTimer
    @Inject
    lateinit var metronome: Metronome
    val practiceState = PracticeState()

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
                is ExerciseEvent.PowerClick -> {
                    exerciseTimer.handleClick(event.state)
                    exerciseSetTimer.handleClick(event.state)
                    metronome.handleClick(event.state)
                    practiceState.handleClick(event.state)
                }
                is ExerciseEvent.OnPause -> {
                    exerciseTimer.onPause()
                    exerciseSetTimer.onPause()
                    metronome.onPause()
                    practiceState.onPause()
                }
            }
        }
    }

    override fun onPause() {
        exerciseViewModel.onPause()
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
            exerciseSetTimer = exerciseSetTimer
            exerciseTimer = exerciseTimer
        }
        return binding
    }
}
