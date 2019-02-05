package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData
import kotlinx.android.synthetic.main.fragment_excercise_set.*


class ExerciseFragment : Fragment() {

    var timerIsOn = false
    var timerRunnable: Runnable = object : Runnable {

        override fun run() {
            overall_time.setText(String.format("%d:%02d", 30000, 200))
            timerHandler.postDelayed(this, 500)
        }
    }

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    private lateinit var viewModel: ExerciseViewModel
    val timerHandler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_excercise_set, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExerciseViewModel().javaClass)
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
            if (timerIsOn) {
                timerIsOn = false
                stopTimer()
            } else {
                timerIsOn = true
                startTimer()
            }
        }
    }

    private fun stopTimer() {
        timerHandler.removeCallbacks(timerRunnable)
    }

    private fun startTimer() {
        timerHandler.postDelayed(timerRunnable, 0)
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
        var seconds = 0.0
        if (exerciseList != null) {
            for (i in exerciseList) {
                val value = i.time
                seconds += value
            }
        }
        val minutes = seconds / 60
//        timerRunnable = object : Runnable {
//
//            override fun run() {
//                overall_time.setText(String.format("%d:%02d", minutes, seconds))
//                timerHandler.postDelayed(this, 500)
//            }
//        }
    }


}
