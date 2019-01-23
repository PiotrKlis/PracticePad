package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.ExerciseList.external.ExerciseSetEntity
import kotlinx.android.synthetic.main.fragment_exerciseset_list.*

class ExerciseListFragment : Fragment() {

    private lateinit var viewModel: ExerciseSetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exerciseset_list, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = ExerciseSetAdapter()
        recycler_list.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(ExerciseSetViewModel().javaClass)

        viewModel.getExerciseSets()?.observe(this, Observer<List<ExerciseSetEntity>>
        { exerciseSets -> adapter.setItems(exerciseSets) })

    }

    companion object {
        fun newInstance() = ExerciseListFragment()
    }
}
