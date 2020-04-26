package com.piotr.practicepad.metronome

import android.media.MediaPlayer
import com.piotr.practicepad.exercise.PracticeState.State
import com.piotr.practicepad.exercise.PracticeState.State.*
import java.util.*
import javax.inject.Inject

class Metronome @Inject constructor(private val mediaPlayer: MediaPlayer) {
    private var timer = Timer()
    private var tempo: Long = -1
    private lateinit var timerTask: TimerTask

    fun setData(tempo: Int) {
        this.tempo = tempo.toLong()
    }

    fun handleClick(state: State) {
        when (state) {
            ON -> runMetronome()
            OFF -> timer.cancel()
            RESTART -> runMetronome()
        }
    }

    fun stop() {
        timer.cancel()
    }

    private fun runMetronome() {
        timer = Timer()
        timer.scheduleAtFixedRate(getTimerTask(), 0, tempo)
    }

    private fun getTimerTask(): TimerTask {
        return object : TimerTask() {
            override fun run() {
                mediaPlayer.start()
            }

            override fun cancel(): Boolean {
                mediaPlayer.pause()
                return super.cancel()
            }
        }
    }
}
