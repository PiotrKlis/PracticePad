package com.piotr.practicepad.metronome

import android.media.MediaPlayer
import java.util.*
import javax.inject.Inject

class Metronome @Inject constructor(private val mediaPlayer: MediaPlayer) : TimerTask() {

    override fun run() {
        mediaPlayer.start()
    }

    override fun cancel(): Boolean {
        mediaPlayer.pause()
        return super.cancel()
    }
}