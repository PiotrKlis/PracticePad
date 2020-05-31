package com.piotr.practicepad.exerciseSetDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseSetDetailBinding
import com.piotr.practicepad.utils.BaseFragment

class ExerciseSetDetailsFragment: BaseFragment() {
    private val viewModel: ExerciseSetDetailViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentExerciseSetDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_set_detail, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
