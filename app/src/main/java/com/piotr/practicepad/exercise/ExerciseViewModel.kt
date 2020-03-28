package com.piotr.practicepad.exercise

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseDataRepository
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.utils.secondsToMiliseconds
import java.util.*
import javax.inject.Inject

private const val FIRST_ITEM = 0
private const val ONE_SECOND = 1000L

class ExerciseViewModel @Inject constructor(
    private val mediaPlayer: MediaPlayer
) : ViewModel() {
    val state: LiveData<ExerciseState>
        get() = mutableExerciseState
    val exerciseTimerState: LiveData<ExerciseTimer>
        get() = mutableExerciseTimer
    val exerciseSetTimerState: LiveData<ExerciseSetTimer>
        get() = mutableExerciseSetTimer
    val isTimerOn = ObservableField(State.OFF)

    enum class State {
        ON, OFF, RESTART
    }

    private val mutableExerciseState =
        MutableLiveData<ExerciseState>().apply { value = ExerciseState() }
    private val mutableExerciseTimer =
        MutableLiveData<ExerciseTimer>().apply { value = ExerciseTimer() }
    private val mutableExerciseSetTimer =
        MutableLiveData<ExerciseSetTimer>().apply { value = ExerciseSetTimer() }

    private lateinit var setTimer: CountDownTimer
    private lateinit var exerciseTimer: CountDownTimer
    private lateinit var timer: Timer
    private var savedState = ExerciseSet()

    fun startNewExerciseSet() {
        ExerciseDataRepository().getActiveExerciseSet().let { activeExerciseSet ->
            if (savedState != activeExerciseSet) {
                isTimerOn.set(State.OFF)
                mutableExerciseState.value =
                    ExerciseState(
                        setName = activeExerciseSet.name,
                        setTimeLeft = getOverallTime(activeExerciseSet.exerciseList),
                        exerciseImage = activeExerciseSet.exerciseList[FIRST_ITEM].image,
                        exerciseName = activeExerciseSet.exerciseList[FIRST_ITEM].name,
                        nextExerciseName = getNextExerciseName(
                            activeExerciseSet.exerciseList,
                            FIRST_ITEM
                        ),
                        exercisesLeft = Pair(FIRST_ITEM, activeExerciseSet.exerciseList.size),
                        currentExerciseIndex = FIRST_ITEM,
                        exerciseList = activeExerciseSet.exerciseList,
                        exerciseTimeLeft = activeExerciseSet.exerciseList[FIRST_ITEM].time,
                        tempo = activeExerciseSet.tempo
                    )
                mutableExerciseTimer.value =
                    ExerciseTimer(activeExerciseSet.exerciseList[FIRST_ITEM].time)
                mutableExerciseSetTimer.value =
                    ExerciseSetTimer(getOverallTime(activeExerciseSet.exerciseList))
                savedState = activeExerciseSet
            }
        }
    }

    fun powerClick() {
        when (isTimerOn.get()) {
            State.ON -> {
                isTimerOn.set(State.OFF)
                stopSetTimer()
                stopExerciseTimer()
                stopMetronome()
            }
            State.OFF -> {
                isTimerOn.set(State.ON)
                startSetTimer()
                startExerciseTimer()
                startMetronome()
            }
            State.RESTART -> {
                isTimerOn.set(State.ON)
                restart()
                startSetTimer()
                startExerciseTimer()
                startMetronome()
            }
        }
    }

    private fun restart() {
        ExerciseDataRepository().getActiveExerciseSet().let { activeExerciseSet ->
            mutableExerciseState.value =
                ExerciseState(
                    setName = activeExerciseSet.name,
                    setTimeLeft = getOverallTime(activeExerciseSet.exerciseList),
                    exerciseImage = activeExerciseSet.exerciseList[FIRST_ITEM].image,
                    exerciseName = activeExerciseSet.exerciseList[FIRST_ITEM].name,
                    nextExerciseName = getNextExerciseName(
                        activeExerciseSet.exerciseList,
                        FIRST_ITEM
                    ),
                    exercisesLeft = Pair(FIRST_ITEM, activeExerciseSet.exerciseList.size),
                    currentExerciseIndex = FIRST_ITEM,
                    exerciseList = activeExerciseSet.exerciseList,
                    exerciseTimeLeft = activeExerciseSet.exerciseList[FIRST_ITEM].time,
                    tempo = activeExerciseSet.tempo
                )
            mutableExerciseSetTimer.value = ExerciseSetTimer(getOverallTime(activeExerciseSet.exerciseList))
            mutableExerciseTimer.value = ExerciseTimer(activeExerciseSet.exerciseList[FIRST_ITEM].time)
        }
    }

    private fun getOverallTime(exerciseList: ArrayList<Exercise>): Long {
        return exerciseList
            .map { it.time }
            .sum()
    }

    private fun getNextExerciseName(exerciseList: ArrayList<Exercise>, index: Int): String {
        var result = "Last one"
        if (index + 1 < exerciseList.size) {
            result = exerciseList[index + 1].name
        }
        return result
    }

    private fun startSetTimer() {
//        setTimer = object :
//            CountDownTimer(mutableExerciseSetTimer.value?.timeLeft ?: ONE_SECOND, ONE_SECOND) {
//            override fun onTick(milisUntilFinished: Long) {
//                mutableExerciseSetTimer.value?.let { state ->
//                    mutableExerciseSetTimer.value = state.copy(timeLeft = milisUntilFinished)
//                }
//            }
//
//            override fun onFinish() {
//                setTimer.cancel()
//                isTimerOn.set(State.RESTART)
//                stopMetronome()
//            }
//        }.start()
    }

    private fun stopSetTimer() {
        setTimer.cancel()
    }

    private fun startExerciseTimer() {
        exerciseTimer = object :
            CountDownTimer(mutableExerciseTimer.value?.timeLeft ?: ONE_SECOND, ONE_SECOND) {
            override fun onTick(milisUntilFinished: Long) {
//                Log.d("AAA", milisUntilFinished.toString())
                    mutableExerciseTimer.postValue(ExerciseTimer(timeLeft = milisUntilFinished))
            }

            override fun onFinish() {
                exerciseTimer.cancel()
                mutableExerciseState.value?.let { state ->
                    if (state.currentExerciseIndex + 1 < state.exerciseList.size) {
                        mutableExerciseState.value =
                            state.copy(
                                currentExerciseIndex = state.currentExerciseIndex + 1,
                                nextExerciseName = getNextExerciseName(
                                    state.exerciseList,
                                    state.currentExerciseIndex + 1
                                ),
                                exercisesLeft = Pair(
                                    state.currentExerciseIndex + 1,
                                    state.exerciseList.size
                                ),
                                exerciseName = state.exerciseList[state.currentExerciseIndex + 1].name,
                                exerciseImage = state.exerciseList[state.currentExerciseIndex + 1].image,
                                exerciseTimeLeft = state.exerciseList[state.currentExerciseIndex + 1].time
                            )
                        mutableExerciseTimer.value = ExerciseTimer(state.exerciseList[state.currentExerciseIndex + 1].time)
                        startExerciseTimer()
                    }
                }
            }
        }.start()
    }

    private fun stopExerciseTimer() {
        exerciseTimer.cancel()
    }

    fun pauseTimers() {
        if (isTimerOn.get() == State.ON) {
            isTimerOn.set(State.OFF)
            stopSetTimer()
            stopExerciseTimer()
            stopMetronome()
        }
    }

    private fun startMetronome() {
//        timer = Timer()
//        mutableExerciseState.value?.let {
//            val tickPeriod = (60.0 / it.tempo * 1000.0).toLong()
//            timer.scheduleAtFixedRate(object : TimerTask() {
//                override fun run() {
//                    mediaPlayer.start()
//                }
//
//            }, 0, tickPeriod)
//        }
    }

    private fun stopMetronome() {
        mediaPlayer.pause()
        timer.cancel()
    }
}

