package com.piotr.practicepad.exerciseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExercisesetListBinding
import com.piotr.practicepad.utils.BaseFragment

class ExerciseListFragment : BaseFragment(), CheckBoxHandler {

    private val viewModel: ExerciseSetViewModel by viewModels { viewModelFactory }
    private var adapter: ExerciseSetAdapter = ExerciseSetAdapter(this).apply { setHasStableIds(true) }
    private lateinit var binding: FragmentExercisesetListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exerciseset_list, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerList.adapter = adapter
    }

    override fun click(id: Int) {
        viewModel.onCheckboxClick(id)
        adapter.notifyDataSetChanged()
    }

    override fun shouldBeChecked(id: Int): Boolean = viewModel.isSetActive(id)
}
