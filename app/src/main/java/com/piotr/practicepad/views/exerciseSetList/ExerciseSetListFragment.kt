package com.piotr.practicepad.views.exerciseSetList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseListBinding
import com.piotr.practicepad.views.exerciseSet.ExerciseSetDetailsFragmentArgs
import com.piotr.practicepad.utils.BaseFragment
import com.piotr.practicepad.utils.NavigationHandler

class ExerciseSetListFragment : BaseFragment(), CheckBoxHandler,
    NavigationHandler {
    private val viewModelSet: ExerciseSetListViewModel by viewModels { viewModelFactory }
    private val adapterSet: ExerciseSetListAdapter =
        ExerciseSetListAdapter(this, this).apply { setHasStableIds(true) }
    private lateinit var binding: FragmentExerciseListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_list, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModelSet
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerList.adapter = adapterSet
        viewModelSet.getExerciseSets()
        binding.addExerciseSet.setOnClickListener { findNavController().navigate(R.id.action_exerciseSetListFragment_to_exerciseAdd) }
    }

    override fun checkBoxClick(id: Int) {
        viewModelSet.onCheckboxClick(id)
        adapterSet.notifyDataSetChanged()
    }

    override fun shouldBeChecked(id: Int): Boolean = viewModelSet.isSetActive(id)
    override fun navigationClick(id: Int) {
        findNavController().navigate(
            R.id.action_exerciseSetListFragment_to_exerciseSetDetailFragment,
            ExerciseSetDetailsFragmentArgs(id).toBundle()
        )
    }
}
