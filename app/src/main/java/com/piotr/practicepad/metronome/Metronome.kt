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
    var tempo: Long? = null

    fun handleClick(state: State, newTempo: Long?) {
        when (state) {
            ON -> {
                tempo = newTempo
                start()
            }
            OFF -> stop()
            RESTART -> {
                tempo = newTempo
                start()
            }
        }
    }

    fun stop() {
        timer.cancel()
    }

    fun updateTempo() {

    }

    fun start() {
        timer.cancel()
        tempo?.let {
            timer = Timer()
            timer.schedule(timerTask {
                mediaPlayer.start()
            }, 0L, it.bpmToMilliseconds())
        } ?: Log.e(this::class.simpleName, "Tempo is null")
    }
}
