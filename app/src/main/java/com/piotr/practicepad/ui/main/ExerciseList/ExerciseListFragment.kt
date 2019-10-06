package com.piotr.practicepad.ui.main.ExerciseList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import kotlinx.android.synthetic.main.fragment_exerciseset_list.*

class ExerciseListFragment : Fragment(), CheckBoxListener {

    private var adapter: ExerciseSetAdapter = ExerciseSetAdapter()
    private val viewModel: ExerciseSetViewModel by lazy {
        ViewModelProviders.of(this).get(ExerciseSetViewModel().javaClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exerciseset_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_list.adapter = adapter
        adapter.setListener(this)
        adapter.setItems(viewModel.getExerciseSets())
    }

    override fun checkboxClick() {
        adapter.notifyDataSetChanged()
    }
}
