package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import kotlinx.android.synthetic.main.fragment_excercise_set.*


class ExerciseFragment : Fragment() {

    var isTimerOn = false
    var currentOverallTimeValue: Long = 0
    var currentActiveExerciseTimeValue: Long = 0
    var overallTimer: CountDownTimer? = null
    var activeExerciseTimer: CountDownTimer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_excercise_set, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: ExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel().javaClass)

        viewModel.getActiveExerciseSet()?.observe(this, Observer<ExerciseSet>
        { exercise -> renderElements(exercise?.exerciseList) })
    }

    private fun renderElements(exerciseList: List<Exercise>?) {
        initializeOverallTimeValue(exerciseList)
        initializeExercisesDone(exerciseList?.size)
        initializeNextExercise(exerciseList)
        initializeCurrentExerciseName(exerciseList)
        initializeTimeLeftOfCurrentExerciseValue(exerciseList)
        initializeImageOfCurrentExercise(exerciseList)
        initializePowerButton()
    }

    private fun initializePowerButton() {
        power.setOnClickListener {
            if (isTimerOn) {
                stopTimers()
            } else {
                startOverallTimer()
                startActiveExerciseTimer()
            }
        }
    }

    private fun startOverallTimer() {
        overallTimer = object : CountDownTimer(currentOverallTimeValue, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentOverallTimeValue = millisUntilFinished
                overall_time.text = convertIntoCorrectTimerFormat(millisUntilFinished)
            }

            override fun onFinish() {
                isTimerOn = false
                overallTimer?.cancel()
                power.setImageResource(R.drawable.ic_play_circle_filled_black_24dp)
            }
        }.start()
        power.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp)
        isTimerOn = true
    }

    private fun startActiveExerciseTimer() {
        activeExerciseTimer = object : CountDownTimer(currentActiveExerciseTimeValue, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentActiveExerciseTimeValue = millisUntilFinished
                current_exercise_time_left.text = convertIntoCorrectTimerFormat(millisUntilFinished)
            }

            override fun onFinish() {
                isTimerOn = false
                activeExerciseTimer?.cancel()
                power.setImageResource(R.drawable.ic_play_circle_filled_black_24dp)
            }
        }.start()
        power.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp)
        isTimerOn = true
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
