package com.piotr.practicepad.ui.main.data

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.CountDownTimer
import android.view.View
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.ExerciseTimer
import com.piotr.practicepad.ui.main.SingleLiveEvent
import com.piotr.practicepad.ui.main.utils.Helper

class ExerciseViewModel : ViewModel() {
    var overallTimer: CountDownTimer? = null
    var activeExerciseTimer: CountDownTimer? = null
    lateinit var exerciseSet: ExerciseSet
    lateinit var exerciseSetTitle: String
    var currentExerciseNumber: Int = 0

    var exerciseList = ArrayList<Exercise>()

    val overallTime = ObservableField<String>()
    val exercisesDone = ObservableField<String>()
    val nextExerciseName = ObservableField<String>()
    val currentExerciseName = ObservableField<String>()
    val currentExerciseTimeLeft = ObservableField<String>()
    val currentExerciseImage = ObservableField<Int>()
    val isTimerOn = ObservableBoolean(false)
    val refreshViewEvent = SingleLiveEvent<Void>()

    fun fetchCurrentExerciseSet() {
        exerciseSet = ExerciseDataRepository().getActiveExerciseSet()
        exerciseList = exerciseSet.exerciseList
    }

    fun renderData() {
        overallTime.set(getOverallTime())
        exercisesDone.set(getExercisesDone())
        nextExerciseName.set(getNextExerciseName())
        currentExerciseName.set(getCurrentExerciseName())
        currentExerciseTimeLeft.set(getTimeLeft())
        currentExerciseImage.set(getImage())
    }

    private fun getImage(): Int {
        return exerciseList[currentExerciseNumber].image
    }

    private fun getTimeLeft(): String {
        return Helper.convertIntoMinutesSeoconds(exerciseList[currentExerciseNumber].time)
    }

    private fun getCurrentExerciseName(): String {
        return exerciseList[currentExerciseNumber].title
    }

    private fun getCurrentExerciseTime(): Long {
        return exerciseList[currentExerciseNumber].time
    }

    fun runTimer() {
        if (isTimerOn.get()) {
            isTimerOn.set(false)
            startSetTimer(getCurrentExerciseTime())
        } else {
            isTimerOn.set(true)
            ExerciseTimer().start(getCurrentExerciseTime())
        }
    }

    private fun getOverallTime(): String {
        var seconds: Long = 0
        for (i in exerciseList) {
            val value = i.time
            seconds += value
        }
        return Helper.convertIntoMinutesSeoconds(seconds)
    }

    private fun getExercisesDone(): String {
        val current = currentExerciseNumber + 1
        return "$current/${getListSize()}"
    }


    private fun getNextExerciseName(): String {
        return if (currentExerciseNumber + 1 <= exerciseList.size) {
            exerciseList[currentExerciseNumber].title
        } else {
            ""
        }
    }

    private fun getListSize(): Int {
        return exerciseList.size
    }

    fun onExerciseFinished() {
        currentExerciseNumber += 1
        if (currentExerciseNumber < getListSize()) {
            ExerciseTimer().start(exerciseList[currentExerciseNumber].time)
        }
    }


    companion object {
        @JvmStatic
        @BindingAdapter("android:background")
        fun setImageButton(view: View, isTimerOn: Boolean) {
            if (isTimerOn) {
                view.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp)
            } else {
                view.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp)
            }
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setImage(view: View, currentExerciseImage: Int) {
            view.setBackgroundResource(currentExerciseImage)
        }
    }

    private fun startSetTimer(time: Long) {
        overallTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                overallTime.set(millisUntilFinished.toString())
//                refreshViewEvent.call()
            }

            override fun onFinish() {
                overallTimer?.cancel()
            }
        }.start()

    }

    fun stop() {

    }

}
