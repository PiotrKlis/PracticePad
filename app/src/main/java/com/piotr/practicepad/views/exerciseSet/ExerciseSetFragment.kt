package com.piotr.practicepad.views.exerciseSet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseSetBinding
import com.piotr.practicepad.utils.BaseFragment

class ExerciseSetFragment : BaseFragment(), Editor {
    private val viewModel: ExerciseSetViewModel by viewModels { viewModelFactory }
    private val adapter = ExerciseSetAdapter(this)
    private val args: ExerciseSetFragmentArgs by navArgs()
    private lateinit var binding: FragmentExerciseSetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise_set,
            container,
            false
        )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.renderData(args.exerciseSetId)
        binding.recyclerList.adapter = adapter
//        binding.fabAddExercise.setOnClickrecyclerListener { findNavController().navigate() }
    }

    override fun delete(position: Int) {
//        binding.recyclerList.adapter?.notifyItemRemoved(position)
        viewModel.delete(position)
        binding.recyclerList.adapter?.notifyDataSetChanged()
    }

    override fun moveUp(position: Int) {
        viewModel.moveUp(position)
//        binding.recyclerList.adapter?.notifyItemMoved(position, position - 1)
//        binding.recyclerList.adapter?.notifyItemMoved(position -1, position)
        binding.recyclerList.adapter?.notifyDataSetChanged()
        Log.d("AAA move up", "$position to ${position - 1}")
    }

    override fun moveDown(position: Int) {
        viewModel.moveDown(position)
//        binding.recyclerList.adapter?.notifyItemMoved(position, position + 1)
//        binding.recyclerList.adapter?.notifyItemMoved(position + 1, position)
        binding.recyclerList.adapter?.notifyDataSetChanged()
        Log.d("AAA move down", "$position to ${position + 1}")
    }
}
