package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.piotr.practicepad.R

class ExerciseFragment : Fragment() {

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    private lateinit var viewModel: ExerciseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_exerciseset, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExerciseViewModel().javaClass)
        viewModel.getExercises()?.observe(this, Observer<List<Exercise>>
        {
                exercises -> Toast.makeText(context, exercises?.get(0)?.title, Toast.LENGTH_LONG).show()
        })
    }
}
