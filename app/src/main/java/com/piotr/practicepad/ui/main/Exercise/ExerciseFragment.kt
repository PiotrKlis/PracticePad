package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.ExerciseSetViewModel

class ExerciseFragment : Fragment() {

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    private lateinit var viewModel: ExerciseSetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_exerciseset, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExerciseSetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
