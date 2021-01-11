package com.piotr.practicepad.views.addExerciseSet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseSetAddBinding
import com.piotr.practicepad.utils.BaseFragment

class AddExerciseSetFragment : BaseFragment() {
    private lateinit var binding: FragmentExerciseSetAddBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_exercise_set, container, false)
        return binding.root
    }
}