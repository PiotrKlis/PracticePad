package com.piotr.practicepad.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.exercise.PracticeState.State.*

class PracticeState {
    val state: LiveData<State> get() = mutableState
    private val mutableState = MutableLiveData(OFF)

    fun setState(state: State) {
        when (state) {
            ON -> mutableState.value = ON
            OFF -> mutableState.value = OFF
            RESTART -> mutableState.value = RESTART
        }
    }

    fun onPause() {
        when (mutableState.value) {
            ON -> mutableState.value = OFF
            OFF -> mutableState.value = OFF
            RESTART -> mutableState.value = RESTART
        }
    }

    enum class State {
        ON, OFF, RESTART
    }
}
