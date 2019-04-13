package com.piotr.practicepad.ui.main

import android.os.CountDownTimer
import com.piotr.practicepad.ui.main.data.ExerciseViewModel

class ExerciseTimer : Timer {

    var overallTimer: CountDownTimer? = null

    override fun start(time: Long) {
        startSetTimer(time)
    }

    private fun startSetTimer(time: Long) {
        overallTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                ExerciseViewModel().overallTime.set(millisUntilFinished.toString())
                ExerciseViewModel().refreshViewEvent.call()
            }

            override fun onFinish() {
                ExerciseViewModel().onExerciseFinished()
                overallTimer?.cancel()
            }
        }.start()

    }

    override fun stop() {

    }
}

/*
    private fun startOverallTimer() {
        overallTimer = object : CountDownTimer(currentExerciseTimeLeft, 1000) {
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
    }*/