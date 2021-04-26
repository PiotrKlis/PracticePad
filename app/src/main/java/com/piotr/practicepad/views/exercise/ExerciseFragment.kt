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


class ExerciseFragment : BaseFragment(), MetronomeService.TickListener {
    private val viewModel: ExerciseViewModel by viewModels { viewModelFactory }
    protected var metronomeService: MetronomeService? = null
    protected var isBound = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getBinding(inflater, container).root

    @FlowPreview
    @RequiresApi(Build.VERSION_CODES.O)
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
        bindService()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.metronomeEvent.collect { event ->
                when (event) {
                    MetronomeEvent.Start -> metronomeService?.play()
                    MetronomeEvent.Stop -> metronomeService?.pause()
                    is MetronomeEvent.TempoChange -> metronomeService?.setBpm(event.tempo)
                }
            }
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

    protected val mConnection: ServiceConnection = object : ServiceConnection {
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
//        activity?.runOnUiThread {beatsView.nextBeat()}
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
