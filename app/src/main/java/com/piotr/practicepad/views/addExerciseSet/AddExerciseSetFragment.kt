package com.piotr.practicepad.views.addExerciseSet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentAddExerciseSetBinding
import com.piotr.practicepad.utils.BaseFragment
import com.piotr.practicepad.views.exerciseSet.ExerciseSetAdapter

class AddExerciseSetFragment : BaseFragment() {
    private val viewModel: AddExerciseSetViewModel by viewModels { viewModelFactory }
    private val adapter = AddExerciseSetAdapter().apply { setHasStableIds(true) }
    private lateinit var binding: FragmentAddExerciseSetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_exercise_set, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getExercises()
        binding.recyclerList.adapter = adapter
    }
}