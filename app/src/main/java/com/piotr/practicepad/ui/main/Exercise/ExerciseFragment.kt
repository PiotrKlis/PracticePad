package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData
import kotlinx.android.synthetic.main.fragment_excercise_set.*

class ExerciseFragment : Fragment() {

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    private lateinit var viewModel: ExerciseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_excercise_set, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExerciseViewModel().javaClass)
        viewModel.getExercises()?.observe(this, Observer<ExerciseSetData>
        {
                exercise -> renderElements(exercise)
        })
    }

    private fun renderElements(exercise: ExerciseSetData?) {
        overall_time.setText(exercise?.exerciseDataList?.get(0)?.time.toString())
    }
}
