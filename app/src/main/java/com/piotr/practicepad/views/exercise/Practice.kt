package com.piotr.practicepad.views.exercise

import com.piotr.practicepad.views.exercise.Practice.State.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class Practice @Inject constructor() {
    val state get() = mutableState
    private val mutableState = MutableStateFlow(OFF)

    fun setState(state: State) {
        when (state) {
            ON -> mutableState.value = ON
            OFF -> mutableState.value = OFF
            RESTART -> mutableState.value = RESTART
        }
    }

    fun powerClick(state: State) {
        when (state) {
            ON -> mutableState.value = OFF
            OFF -> mutableState.value = ON
            RESTART -> mutableState.value = ON
        }
    }

    fun onPause() {
        if (state.value == ON) mutableState.value = OFF
    }

    enum class State {
        ON, OFF, RESTART
    }
}
