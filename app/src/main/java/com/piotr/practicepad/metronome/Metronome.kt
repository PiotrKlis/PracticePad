package com.piotr.practicepad.metronome

import android.media.MediaPlayer
import com.piotr.practicepad.exercise.PracticeState.State
import com.piotr.practicepad.exercise.PracticeState.State.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

private const val MILLISECONDS_IN_SECOND: Int = 1000
private const val SECONDS_IN_MINUTE: Int = 60

class Metronome @Inject constructor(private val mediaPlayer: MediaPlayer) {
    private var timer = Timer()
    private var tempo: Long = -1

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

    private fun bpmToMiliseconds(bpm: Long): Long =
        (MILLISECONDS_IN_SECOND * (SECONDS_IN_MINUTE / bpm.toDouble())).toLong()

    private fun runMetronome() {
        timer = Timer()
        timer.schedule(timerTask {
            mediaPlayer.start()
        }, 0L, bpmToMiliseconds(tempo))
    }
}
