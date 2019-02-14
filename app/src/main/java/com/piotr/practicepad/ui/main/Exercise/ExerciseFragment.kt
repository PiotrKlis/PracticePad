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
    var currentTimeValue: Long = 0
    var countDownTimer: CountDownTimer? = null
    val KEY_CURRENT_TIME_VALUE = "current_time_value"
    var persistedTimerBundle: Bundle = Bundle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_excercise_set, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: ExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel().javaClass)
        viewModel.getActiveExerciseSet()?.observe(this, Observer<ExerciseSetData>
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

    override fun onPause() {
        super.onPause()
        stopTimer()
        persistedTimerBundle.putLong(KEY_CURRENT_TIME_VALUE, currentTimeValue)
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        isTimerOn = false
        power.setImageResource(R.drawable.ic_play_circle_filled_black_24dp)
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(currentTimeValue, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTimeValue = millisUntilFinished
                overall_time.text = convertIntoCorrectTimerFormat(millisUntilFinished)
            }

            override fun onFinish() {
                isTimerOn = false
                power.setImageResource(R.drawable.ic_play_circle_filled_black_24dp)
            }
        }.start()
        power.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp)
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
        if (persistedTimerBundle.isEmpty) {
            var seconds: Long = 0
            if (exerciseList != null) {
                for (i in exerciseList) {
                    val value = i.time
                    seconds += value
                }
                currentTimeValue = seconds
            }
        } else {
            currentTimeValue = persistedTimerBundle.getLong(KEY_CURRENT_TIME_VALUE)
        }
        overall_time.text = convertIntoCorrectTimerFormat(currentTimeValue)
    }

    private fun convertIntoCorrectTimerFormat(time: Long): String {
        val minutes = (time / 1000).toInt() / 60
        val seconds = (time / 1000).toInt() % 60

        return String.format("%02d:%02d", minutes, seconds)
    }
}
