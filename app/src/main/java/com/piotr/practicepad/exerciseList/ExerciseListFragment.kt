package com.piotr.practicepad.exerciseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseListBinding
import com.piotr.practicepad.exerciseSetDetail.ExerciseSetDetailsFragmentArgs
import com.piotr.practicepad.utils.BaseFragment

class ExerciseListFragment : BaseFragment(), CheckBoxHandler, NavigationHandler {
    private val viewModel: ExerciseListViewModel by viewModels { viewModelFactory }
    private val adapter: ExerciseListAdapter =
        ExerciseListAdapter(this, this).apply { setHasStableIds(true) }
    private lateinit var binding: FragmentExerciseListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_list, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerList.adapter = adapter
    }

    override fun checkBoxClick(id: Int) {
        viewModel.onCheckboxClick(id)
        adapter.notifyDataSetChanged()
    }

    override fun shouldBeChecked(id: Int): Boolean = viewModel.isSetActive(id)
    override fun navigationClick(id: Int) {
        findNavController().navigate(
            R.id.action_navigation_list_to_detail,
            ExerciseSetDetailsFragmentArgs(id).toBundle()
        )
    }
}
