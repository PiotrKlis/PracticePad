package com.piotr.practicepad.views.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExcerciseBinding
import com.piotr.practicepad.timers.ExerciseSetTimerEvent
import com.piotr.practicepad.ui.main.utils.observeEvent
import com.piotr.practicepad.utils.BaseFragment
import com.piotr.practicepad.views.exercise.Practice.State.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class ExerciseFragment : BaseFragment() {
    private val viewModel: ExerciseViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentExcerciseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.renderActiveExerciseSet()
        viewModel.exerciseTimer.timerEvent.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseTimerEvent.ExerciseEnded -> viewModel.renderNextExercise()
            }
        }
        viewModel.exerciseSetTimer.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseSetTimerEvent.ExerciseSetEnded -> viewModel.setEnded()
            }
        }
        GlobalScope.launch {
            viewModel.practice.state.collectLatest { practiceState ->
                when (practiceState) {
                    ON -> binding.power.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp)
                    OFF -> binding.power.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp)
                    RESTART -> binding.power.setBackgroundResource(R.drawable.ic_replay_black_24dp)
                }
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
            model = viewModel
            exerciseSetTimer = viewModel.exerciseSetTimer
            exerciseTimer = viewModel.exerciseTimer
            practice = viewModel.practice
        }
        return binding
    }
}
