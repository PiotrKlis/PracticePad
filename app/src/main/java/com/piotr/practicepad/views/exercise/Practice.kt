package com.piotr.practicepad.views.exercise

import com.piotr.practicepad.views.exercise.Practice.State.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Practice {
    val state get() = mutableState.asStateFlow()
    private val mutableState = MutableStateFlow(OFF)

    suspend fun setState(state: State) {
        when (state) {
            ON -> mutableState.emit(ON)
            OFF -> mutableState.emit(OFF)
            RESTART -> mutableState.emit(RESTART)
        }
    }

    suspend fun onPause() {
        if (state.value == ON) mutableState.emit(OFF)
    }

    enum class State {
        ON, OFF, RESTART
    }
}
