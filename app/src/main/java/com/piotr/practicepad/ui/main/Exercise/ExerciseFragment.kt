package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.ExerciseBinding
import com.piotr.practicepad.ui.main.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_excercise.*


class ExerciseFragment : Fragment() {

    var isTimerOn = false
    var currentOverallTimeValue: Long = 0
    var currentActiveExerciseTimeValue: Long = 0
    var overallTimer: CountDownTimer? = null
    var activeExerciseTimer: CountDownTimer? = null
    lateinit var viewModel: ExerciseViewModel
    lateinit var binding: ExerciseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        val binding: ExerciseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_excercise, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCurrentExerciseSet()
        viewModel.renderData()
        viewModel.refreshViewEvent.observe(this, Observer {
            binding.executePendingBindings()
        })
    }


    override fun onPause() {
        super.onPause()
        stopTimers()
    }

    private fun stopTimers() {
        overallTimer?.cancel()
        activeExerciseTimer?.cancel()
        isTimerOn = false
        power.setImageResource(R.drawable.ic_play_circle_filled_black_24dp)
    }


    private fun initializeImageOfCurrentExercise(exerciseList: List<Exercise>?) {
        if (exerciseList != null) {
            //image.setImageResource()
        }
    }

    private fun initializeCurrentExerciseName(exerciseList: List<Exercise>?) {
        if (exerciseList != null) {
            current_exercise_name.text = exerciseList[0].title
        }
    }

    private fun initializeNextExercise(exerciseList: List<Exercise>?) {
        if (exerciseList != null) {
            next_exercise.text = exerciseList[1].title
        }
    }

    private fun initializeExercisesDone(size: Int?) {
        exercises_done.text = "0"
    }

    private fun initializeTimeLeftOfCurrentExerciseValue(exerciseList: List<Exercise>?) {
        if (exerciseList != null) {
            val time = exerciseList[0].time
            current_exercise_time_left.text = convertIntoCorrectTimerFormat(time)
            currentActiveExerciseTimeValue = time
        }
    }

    private fun initializeOverallTimeValue(exerciseList: List<Exercise>?) {
        var seconds: Long = 0
        if (exerciseList != null) {
            for (i in exerciseList) {
                val value = i.time
                seconds += value
            }
            currentOverallTimeValue = seconds
        }

        overall_time.text = convertIntoCorrectTimerFormat(currentOverallTimeValue)
    }

    private fun convertIntoCorrectTimerFormat(time: Long): String {
        val minutes = (time / 1000).toInt() / 60
        val seconds = (time / 1000).toInt() % 60

        return String.format("%02d:%02d", minutes, seconds)
    }
}
