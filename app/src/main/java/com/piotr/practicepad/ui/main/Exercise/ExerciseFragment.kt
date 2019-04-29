package com.piotr.practicepad.ui.main.Exercise

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExcerciseBinding
import com.piotr.practicepad.ui.main.ExerciseViewModel


class ExerciseFragment : Fragment() {

    lateinit var viewModel: ExerciseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        val binding: FragmentExcerciseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_excercise, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCurrentExerciseSet()
        viewModel.renderData()
    }
}
