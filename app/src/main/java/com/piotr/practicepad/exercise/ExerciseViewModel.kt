package com.piotr.practicepad.exercise

import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseDataRepository
import com.piotr.practicepad.exerciseList.ExerciseSet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val FIRST_ITEM = 0
private const val ONE_SECOND = 1000L

class ExerciseViewModel : ViewModel() {
    val state: LiveData<ExerciseState>
        get() = mutableExerciseState
    val isTimerOn = ObservableField(State.OFF)

    enum class State {
        ON, OFF, RESTART
    }

    private val mutableExerciseState =
        MutableLiveData<ExerciseState>().apply { value = ExerciseState() }

    private lateinit var setTimer: CountDownTimer
    private lateinit var exerciseTimer: CountDownTimer
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var couroutine: Job
    private var savedState = ExerciseSet()

    fun startNewExerciseSet(mediaPlayerro: MediaPlayer) {
        mediaPlayer = mediaPlayerro

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
                    exerciseTimeLeft = activeExerciseSet.exerciseList[FIRST_ITEM].time
                )
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
        setTimer = object :
            CountDownTimer(mutableExerciseState.value?.setTimeLeft ?: ONE_SECOND, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseState.value?.let { state ->
                    mutableExerciseState.value = state.copy(setTimeLeft = millisUntilFinished)
                }
            }

            override fun onFinish() {
                setTimer.cancel()
                isTimerOn.set(State.RESTART)
                stopMetronome()
            }
        }.start()
    }

    private fun stopSetTimer() {
        setTimer.cancel()
    }

    private fun startExerciseTimer() {
        exerciseTimer = object :
            CountDownTimer(mutableExerciseState.value?.exerciseTimeLeft ?: ONE_SECOND, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseState.value?.let { state ->
                    mutableExerciseState.value = state.copy(exerciseTimeLeft = millisUntilFinished)
                }
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
        }
    }

    private fun startMetronome() {
        mutableExerciseState.value?.let {
            val tickPeriod = (60.0 / it.tempo * 1000.0).toLong()
            startCoroutineTimer(repeatMillis = tickPeriod) {
                mediaPlayer.start()
            }
        }
    }

    private fun stopMetronome() {
        couroutine.cancel()
    }

    fun startCoroutineTimer(repeatMillis: Long, action: () -> Unit) {
        couroutine = GlobalScope.launch {
                while (true) {
                    action()
                    delay(repeatMillis)
            }
        }
    }
}
