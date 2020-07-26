package com.piotr.practicepad.exerciseSetDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.FragmentExerciseSetDetailBinding
import com.piotr.practicepad.utils.BaseFragment

class ExerciseSetDetailsFragment : BaseFragment(), Editor {
    private val viewModel: ExerciseSetDetailViewModel by viewModels { viewModelFactory }
    private val adapter: ExerciseSetDetailsAdapter = ExerciseSetDetailsAdapter(this)
    private val args: ExerciseSetDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentExerciseSetDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise_set_detail,
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
        binding.list.adapter = this.adapter
    }

    override fun delete(position: Int) {
        binding.list.adapter?.notifyItemRemoved(position)
        Log.d("AAA delete", "$position")
    }

    override fun moveUp(position: Int) {
        viewModel.moveUp(position)
        binding.list.adapter?.notifyItemChanged(position)
        binding.list.adapter?.notifyItemChanged(position - 1)
        binding.list.adapter?.notifyItemMoved(position, position - 1)

//        binding.list.adapter?.notifyDataSetChanged()
        Log.d("AAA move up", "$position to ${position - 1}")
    }

    override fun moveDown(position: Int) {
        viewModel.moveDown(position)
        binding.list.adapter?.notifyItemChanged(position)
        binding.list.adapter?.notifyItemChanged(position + 1)
        binding.list.adapter?.notifyItemMoved(position, position + 1)
//        binding.list.adapter?.notifyDataSetChanged()

        Log.d("AAA move down", "$position to ${position + 1}")
    }
}
