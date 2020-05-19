package com.piotr.practicepad.exerciseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.piotr.practicepad.R
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.extensions.viewModelProvider
import com.piotr.practicepad.utils.BaseFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_exerciseset_list.*
import javax.inject.Inject

class ExerciseListFragment : BaseFragment(), CheckBoxHandler {

    @Inject
    lateinit var sharedPrefs: SharedPrefs
    private val viewModel: ExerciseSetViewModel by viewModels { viewModelFactory }
    private var adapter: ExerciseSetAdapter = ExerciseSetAdapter(this)

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
        adapter.setItems(viewModel.getExerciseSets())
    }

    override fun click(id: Int) {
        sharedPrefs.setActiveSetId(id)
        adapter.notifyDataSetChanged()
    }

    override fun shouldBeChecked(id: Int): Boolean = id == sharedPrefs.getActiveSetId()
}
