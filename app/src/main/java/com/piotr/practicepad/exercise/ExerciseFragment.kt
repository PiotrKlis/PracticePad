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
import com.piotr.practicepad.ui.main.utils.observeEvent
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ExerciseFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ExerciseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModelProvider(viewModelFactory)
        return getBinding(inflater, container).root
    }

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
