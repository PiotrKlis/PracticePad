package com.piotr.practicepad.views.addExercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentAddExerciseBinding
import com.piotr.practicepad.utils.BaseFragment
import com.piotr.practicepad.views.exerciseSet.ExerciseSetFragmentArgs

class AddExerciseFragment : BaseFragment() {
    private val viewModel: AddExerciseViewModel by viewModels { viewModelFactory }
    private val args: AddExerciseFragmentArgs by navArgs()
    private val adapter = AddExerciseAdapter(::onAddExerciseButtonClick).apply { setHasStableIds(true) }
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
        setSearchQueryListener()

    }

    private fun onAddExerciseButtonClick(exerciseId: Int) {
        viewModel.addExerciseToSet(exerciseId, args.exerciseSetId)
        Toast.makeText(context, "Exercise Added!", Toast.LENGTH_SHORT).show()
    }

    private fun setSearchQueryListener() {
        binding.search.setOnQueryTextListener(object : SearchViewBindingAdapter.OnQueryTextChange,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    viewModel.getExercises()
                } else {
                    viewModel.getExercisesForText(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.getExercises()
                } else {
                    viewModel.getExercisesForText(newText)
                }
                return true
            }
        })
    }
}
