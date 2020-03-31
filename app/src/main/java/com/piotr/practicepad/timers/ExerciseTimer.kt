package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private const val ONE_SECOND = 1000L

class ExerciseTimer(time: Long) : CountDownTimer(time, ONE_SECOND) {
    val data: LiveData<Long>
        get() = mutableData
    private val mutableData = MutableLiveData<Long>().apply { value = time }

    override fun onTick(millisUntilFinished: Long) {
        mutableData.postValue(millisUntilFinished)
    }

    override fun onFinish() {
        cancel()
    }
}
