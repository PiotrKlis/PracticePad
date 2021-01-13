package com.piotr.practicepad.metronome

import android.media.SoundPool

class SoundPlayer(private val soundPool: SoundPool, private val soundId: Int) : Player {
    override fun play() {
        soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
    }
}
