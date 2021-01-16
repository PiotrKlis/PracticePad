package com.piotr.practicepad.metronome

import com.piotr.practicepad.extensions.bpmToMilliseconds
import com.piotr.practicepad.views.exercise.Practice
import com.piotr.practicepad.views.exercise.Practice.State.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask


class Metronome @Inject constructor(
    private val player: Player,
    private val practice: Practice
) {
    val tempo get() = mutableTempo.asSharedFlow()
    private val mutableTempo = MutableSharedFlow<Long>()
    private var timer = Timer()

    init {
        GlobalScope.launch {
            practice.state.collectLatest { state ->
                when (state) {
                    ON -> start()
                    OFF, RESTART -> stop()
                }
            }
            tempo.collectLatest { start() }
        }
    }

    suspend fun updateTempo(tempo: Long) {
        mutableTempo.emit(tempo)
    }

    private fun stop() {
        timer.cancel()
    }

    private suspend fun start() {
        timer.cancel()
            timer = Timer()
            timer.schedule(timerTask {
                player.play()
            }, 0L, tempo.first().bpmToMilliseconds())
    }
}
