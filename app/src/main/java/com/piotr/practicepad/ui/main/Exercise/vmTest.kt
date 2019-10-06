package com.piotr.practicepad.ui.main.Exercise

import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.data.repository.ExerciseDataRepository
import com.piotr.practicepad.ui.main.utils.Event
import com.piotr.practicepad.ui.main.utils.NonNullMutableLiveData

private const val FIRST_ITEM = 0

class vmTest : ViewModel() {
    private lateinit var setTimer: CountDownTimer
    private lateinit var exerciseTimer: CountDownTimer

    private val mutableExerciseState = NonNullMutableLiveData(ExerciseState())
    private val mutableExerciseEvent = MutableLiveData<Event<ExerciseState>>()

    private val mutableExerciseSetState = NonNullMutableLiveData(ExerciseSetState())
    private val mutableExerciseSetEvent = MutableLiveData<Event<ExerciseSetState>>()

    val exerciseState: LiveData<ExerciseState>
        get() = mutableExerciseState
    val exerciseEvent: LiveData<Event<ExerciseState>>
        get() = mutableExerciseEvent

    val exerciseSetState: LiveData<ExerciseSetState>
        get() = mutableExerciseSetState
    val exerciseSetEvent: LiveData<Event<ExerciseSetState>>
        get() = mutableExerciseSetEvent

    val isTimerOn = ObservableField(State.OFF)

    enum class State {
        ON, OFF, RESTART
    }

    fun startNewExerciseSet() {
        ExerciseDataRepository().getActiveExerciseSet().let {
            mutableExerciseSetState.value =
                ExerciseSetState(
                    it.title,
                    getOverallTime(it.exerciseList),
                    getNextExerciseName(it.exerciseList, FIRST_ITEM),
                    getExercisesDone(it.exerciseList),
                    FIRST_ITEM
                )

            mutableExerciseState.value =
                ExerciseState(
                    it.exerciseList[FIRST_ITEM].time,
                    it.exerciseList[FIRST_ITEM].title,
                    it.exerciseList[FIRST_ITEM].image
                )
        }
    }

    fun getExerciseListSize() {

    }

    private fun getOverallTime(exerciseList: ArrayList<Exercise>): String {
        return exerciseList
            .map { it.time }
            .sum()
            .convertIntoMinutesAndSeconds()
    }

    private fun getNextExerciseName(exerciseList: ArrayList<Exercise>, index: Int): String {
        var result = "Last one"
        //TODO: Extention function
        if (exerciseList.size < index) {
            result = exerciseList[index].title
        }
        return result
    }

    //TODO: Binding adapter?
    private fun getExercisesDone(exerciseList: ArrayList<Exercise>): String {
        val index = getCurrentIndex()

        return if (exerciseList.size < index) {
            "${mutableExerciseSetState.value.currentExerciseIndex.plus(1)}/${exerciseList.size}"
        } else {
            "${mutableExerciseSetState.value.currentExerciseIndex}/${exerciseList.size}"
        }
    }

    private fun getCurrentIndex(): Int = mutableExerciseSetState.value.currentExerciseIndex

    fun powerClick() {
        when (isTimerOn.get()) {
            State.ON -> {
                isTimerOn.set(State.OFF)
                stopSetTimer()
                stopExerciseTimer()
            }
            State.OFF -> {
                isTimerOn.set(State.ON)
                startSetTimer()
                startExerciseTimer()
            }
            State.RESTART -> {
                isTimerOn.set(State.ON)
                startNewExerciseSet()
                startSetTimer(mutableExerciseSetState.value.timeLeft)
                startExerciseTimer(exerciseSet.exerciseList[mutableExerciseSetState.value?.currentExerciseIndex!!].time)
            }
        }
    }

    private fun startSetTimer() {
        setTimer = object : CountDownTimer(mutableExerciseSetState.value.timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseSetState.value?.let { mutableExerciseSetState.postValue(it.copy(timeLeft = millisUntilFinished.convertIntoMinutesAndSeconds())) }
            }

            override fun onFinish() {
                setTimer.cancel()
                isTimerOn.set(State.RESTART)
            }
        }.start()
    }

    private fun stopSetTimer() {
        setTimer.cancel()
    }

    private fun startExerciseTimer() {
        exerciseTimer = object : CountDownTimer(mutableExerciseState.value?.timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseState.value?.let { mutableExerciseState.postValue(it.copy(timeLeft = millisUntilFinished.convertIntoMinutesAndSeconds())) }
            }

            override fun onFinish() {
                exerciseTimer.cancel()
                mutableExerciseSetState.value?.let {
                    mutableExerciseSetState.postValue(it.copy(currentExerciseIndex = it.currentExerciseIndex + 1))
                    mutableExerciseSetState.postValue(
                        it.copy(
                            nextName = getNextExerciseName(exerciseSet.exerciseList, it.currentExerciseIndex),
                            exercisesLeft = getExercisesDone(exerciseSet.exerciseList)
                        )
                    )
                }

                mutableExerciseState.value?.let {
                    mutableExerciseState.postValue(it.copy())
                }

                mutableExerciseState.value =
                    ExerciseState(
                        timeLeft = exerciseSet.exerciseList[FIRST_ITEM].time.convertIntoMinutesAndSeconds(),
                        title = exerciseSet.exerciseList[FIRST_ITEM].title,
                        image = exerciseSet.exerciseList[FIRST_ITEM].image
                    )

                startExerciseTimer()
            }
        }.start()
    }

    private fun stopExerciseTimer() {
        exerciseTimer.cancel()
    }

}

private fun Long.convertIntoMinutesAndSeconds(): String {
    val minutes = (this / 1000).toInt() / 60
    val seconds = (this / 1000).toInt() % 60

    return String.format("%02d:%02d", minutes, seconds)
}