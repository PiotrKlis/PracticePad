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
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData
import kotlinx.android.synthetic.main.fragment_excercise_set.*


class ExerciseFragment : Fragment() {

    var isTimerOn = false
    var timerRunnable: Runnable? = null
    var startTimeValue: Int = 0
    var currentTimeValue: Long = 0
    var countDownTimer: CountDownTimer? = null

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_excercise_set, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: ExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel().javaClass)
        viewModel.getExercises()?.observe(this, Observer<ExerciseSetData>
        { exercise -> renderElements(exercise?.exerciseDataList) })
    }

    private fun renderElements(exerciseList: List<ExerciseData>?) {
        initializeOverallTimer(exerciseList)
        initializeExercisesDone(exerciseList?.size)
        initializeNextExercise(exerciseList)
        initializeCurrentExerciseName(exerciseList)
        initializeTimeLeftOfCurrentExercise(exerciseList)
        initializeImageOfCurrentExercise(exerciseList)
        initializePowerButton()
    }

    private fun initializePowerButton() {
        power.setOnClickListener {
            if (isTimerOn) {
                isTimerOn = false
                stopTimer()
            } else {
                isTimerOn = true
                startTimer()
            }
        }
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        isTimerOn = false
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(currentTimeValue, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTimeValue = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                isTimerOn = false
            }
        }.start()

        isTimerOn = true
    }

    private fun initializeImageOfCurrentExercise(exerciseList: List<ExerciseData>?) {

    }

    private fun initializeTimeLeftOfCurrentExercise(exerciseList: List<ExerciseData>?) {

    }

    private fun initializeCurrentExerciseName(exerciseList: List<ExerciseData>?) {

    }

    private fun initializeNextExercise(exerciseList: List<ExerciseData>?) {

    }

    private fun initializeExercisesDone(size: Int?) {

    }

    private fun initializeOverallTimer(exerciseList: List<ExerciseData>?) {
        var seconds: Long = 0
        if (exerciseList != null) {
            for (i in exerciseList) {
                val value = i.time
                seconds += value
            }
            currentTimeValue = seconds
        }
    }

    private fun updateCountDownText() {
        val minutes = (currentTimeValue / 1000).toInt() / 60
        val seconds = (currentTimeValue / 1000).toInt() % 60

        val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)

        overall_time.text = timeLeftFormatted
    }
}
