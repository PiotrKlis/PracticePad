package com.piotr.practicepad.exercise

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExcerciseBinding


class ExerciseFragment : Fragment() {

    private lateinit var viewModel: ExerciseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        val binding: FragmentExcerciseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_excercise, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mediaPlayer = MediaPlayer.create(context, R.raw.low_seiko_sq50)
        viewModel.startNewExerciseSet(mediaPlayer)
    }

    override fun onPause() {
        viewModel.pauseTimers()
        super.onPause()
    }
}
