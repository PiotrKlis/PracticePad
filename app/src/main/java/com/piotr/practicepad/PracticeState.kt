package com.piotr.practicepad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.PracticeState.State.*

class PracticeState {
    val state: LiveData<State>
        get() = mutableState
    private val mutableState = MutableLiveData<State>().apply { value = OFF }

    fun handleClick(state: State) {
        when (state) {
            ON -> mutableState.value = ON
            OFF -> mutableState.value = OFF
            RESTART -> mutableState.value = RESTART
        }
    }

    fun onPause() {
        mutableState.value = OFF
    }

    enum class State {
        ON, OFF, RESTART
    }
}
