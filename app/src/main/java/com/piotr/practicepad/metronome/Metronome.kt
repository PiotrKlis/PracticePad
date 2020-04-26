package com.piotr.practicepad.metronome

import android.media.MediaPlayer
import com.piotr.practicepad.exercise.PracticeState.State
import com.piotr.practicepad.exercise.PracticeState.State.*
import java.util.*
import javax.inject.Inject

class Metronome @Inject constructor(
    private val mediaPlayer: MediaPlayer
) {
    private val timer = Timer()
    private var timerTask: TimerTask = object : TimerTask() {
        override fun run() {
            mediaPlayer.start()
        }

        override fun cancel(): Boolean {
            mediaPlayer.pause()
            return super.cancel()
        }
    }

    fun handleClick(state: State, tempo: Int = 0) {
        when (state) {
            ON -> runMetronome(tempo)
            OFF -> timer.cancel()
            RESTART -> runMetronome(tempo)
        }
    }

    fun stop() {
        timer.cancel()
    }

    private fun runMetronome(tempo: Int) {
        timerTask.cancel()
        timer.scheduleAtFixedRate(timerTask, 0, tempo.toLong())
    }

}
