package com.piotr.practicepad.exerciseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.piotr.practicepad.R
import com.piotr.practicepad.data.db.ActiveSetSharedPrefs
import com.piotr.practicepad.extensions.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_exerciseset_list.*
import javax.inject.Inject

class ExerciseListFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPrefs: ActiveSetSharedPrefs
    private lateinit var viewModel: ExerciseSetViewModel
    private var adapter: ExerciseSetAdapter =
        ExerciseSetAdapter(
            ::checkboxClick,
            ::shouldBeChecked
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        return inflater.inflate(R.layout.fragment_exerciseset_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_list.adapter = adapter
        adapter.setItems(viewModel.getExerciseSets())
    }

    private fun checkboxClick(id: Int) {
        sharedPrefs.setActiveSetId(id)
        adapter.notifyDataSetChanged()
    }

    private fun shouldBeChecked(id: Int): Boolean {
        return id == sharedPrefs.getActiveSetId()
    }
}
