package com.piotr.practicepad.views.addExercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.viewModels
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentAddExerciseBinding
import com.piotr.practicepad.utils.BaseFragment

class AddExerciseFragment : BaseFragment() {
    private val viewModel: AddExerciseViewModel by viewModels { viewModelFactory }
    private val adapter = AddExerciseAdapter().apply { setHasStableIds(true) }
    private lateinit var binding: FragmentAddExerciseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_exercise, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getExercises()
        binding.recyclerList.adapter = adapter
        binding.search.setOnQueryTextListener(object : SearchViewBindingAdapter.OnQueryTextChange,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getExercisesForText(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getExercisesForText(newText)
                return true
            }
        })
    }
}


