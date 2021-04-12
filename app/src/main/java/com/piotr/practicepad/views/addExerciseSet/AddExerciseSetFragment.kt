package com.piotr.practicepad.views.addExerciseSet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R

class AddExerciseSetFragment : Fragment() {

    companion object {
        fun newInstance() = AddExerciseSetFragment()
    }

    private lateinit var viewModel: AddExerciseSetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_exercise_set, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddExerciseSetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}