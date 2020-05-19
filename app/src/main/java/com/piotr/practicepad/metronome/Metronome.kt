package com.piotr.practicepad.metronome

import android.media.MediaPlayer
import android.util.Log
import com.piotr.practicepad.exercise.PracticeState.State
import com.piotr.practicepad.exercise.PracticeState.State.*
import com.piotr.practicepad.extensions.bpmToMilliseconds
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

class Metronome @Inject constructor(private val mediaPlayer: MediaPlayer) {
    private var timer = Timer()

    fun handleClick(state: State, tempo: Long?) {
        when (state) {
            ON -> run(tempo)
            OFF -> timer.cancel()
            RESTART -> run(tempo)
        }
    }

    fun stop() {
        timer.cancel()
    }

    fun changeTempo(tempo: Long) {
        timer.cancel()
        run(tempo)
    }

    private fun run(tempo: Long?) {
        tempo?.let {
            timer = Timer()
            timer.schedule(timerTask {
                mediaPlayer.start()
            }, 0L, it.bpmToMilliseconds())
        } ?: Log.e(this::class.simpleName, "Tempo is null")
    }
}
