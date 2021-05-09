package com.piotr.practicepad.views.exercise

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExcerciseBinding
import com.piotr.practicepad.metronome.MetronomeService
import com.piotr.practicepad.ui.main.utils.observeEvent
import com.piotr.practicepad.utils.BaseFragment
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@FlowPreview
class ExerciseFragment : BaseFragment(), MetronomeService.TickListener {
    private val viewModel: ExerciseViewModel by viewModels { viewModelFactory }
    private var metronomeService: MetronomeService? = null
    private var isBound = false
    private var binding: FragmentExcerciseBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container)
        return binding?.root
    }

    @FlowPreview
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.renderActiveExerciseSet()
        metronomeService?.setBpm(viewModel.state.value?.tempo?.toInt() ?: 100)
        viewModel.exerciseTimer.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseEvent.NextExercise -> viewModel.renderNextExercise(event.position)
                else -> {
                } //no-op
            }
        }
        viewModel.exerciseSetTimer.event.observeEvent(viewLifecycleOwner) { event ->
            when (event) {
                is ExerciseEvent.SetEnded -> viewModel.setEnded()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.metronomeEvent.collect { event ->
                when (event) {
                    MetronomeEvent.Start -> metronomeService?.play()
                    MetronomeEvent.Stop -> metronomeService?.pause()
                    is MetronomeEvent.TempoChange -> metronomeService?.setBpm(event.tempo)
                }
            }
        }
        bindService()
        handleTempoButtonsLongClicks()
    }

    private fun handleTempoButtonsLongClicks() {
        binding?.addTempoButton?.setOnLongClickListener {
            metronomeService?.bpm?.plus(10)?.toLong()?.let { newTempo ->
                viewModel.longTempoClick(newTempo)
            }
            true
        }
        binding?.substractTempoButton?.setOnLongClickListener {
            metronomeService?.bpm?.minus(10)?.toLong()?.let { newTempo ->
                viewModel.longTempoClick(newTempo)
            }
            true
        }
    }

    private fun bindService() {
        activity?.bindService(
            Intent(
                activity,
                MetronomeService::class.java
            ), mConnection, Context.BIND_AUTO_CREATE
        )
        isBound = true
    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            metronomeService = (service as MetronomeService.MetronomeBinder).getService()
            metronomeService?.addTickListener(this@ExerciseFragment)
        }

        override fun onServiceDisconnected(className: ComponentName) {
            metronomeService = null
            isBound = false
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
            practice = viewModel.practiceState
        }
        return binding
    }

    override fun onTick(interval: Int) {
        //no-op
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            metronomeService?.removeTickListener(this)
            // Detach our existing connection.
            requireActivity().unbindService(mConnection)
            isBound = false
        }
    }
}
