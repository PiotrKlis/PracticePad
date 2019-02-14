package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.SharedPrefs
import kotlinx.android.synthetic.main.fragment_exerciseset_list.*

class ExerciseListFragment : Fragment(), ExerciseListCheckBoxListener {

    private var adapter: ExerciseSetAdapter? = ExerciseSetAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exerciseset_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ExerciseSetViewModel().javaClass)
        val sets = viewModel.getExerciseSets()

        recycler_list.adapter = adapter
        adapter?.setListener(this)
        adapter?.setItems(sets)
    }

    override fun checkboxClick() {
        adapter?.notifyDataSetChanged()
    }
}
