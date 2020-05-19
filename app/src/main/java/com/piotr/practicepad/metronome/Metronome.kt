package com.piotr.practicepad.metronome

import android.media.MediaPlayer
import com.piotr.practicepad.exercise.PracticeState.State
import com.piotr.practicepad.exercise.PracticeState.State.*
import com.piotr.practicepad.extensions.bpmToMilliseconds
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

class Metronome @Inject constructor(private val mediaPlayer: MediaPlayer) {
    private var timer = Timer()
    private var tempo: Long = -1

    fun setData(tempo: Int) {
        this.tempo = tempo.toLong()
    }

    fun handleClick(state: State) {
        when (state) {
            ON -> run()
            OFF -> timer.cancel()
            RESTART -> run()
        }
    }

    fun stop() {
        timer.cancel()
    }

    fun changeTempo(tempo: Long) {
        this.tempo = tempo
        timer.cancel()
        run()
    }

    private fun run() {
        timer = Timer()
        timer.schedule(timerTask {
            mediaPlayer.start()
        }, 0L, tempo.bpmToMilliseconds())
    }
}
