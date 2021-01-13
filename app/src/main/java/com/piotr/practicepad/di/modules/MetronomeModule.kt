package com.piotr.practicepad.di.modules

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.piotr.practicepad.R
import com.piotr.practicepad.metronome.Player
import com.piotr.practicepad.metronome.SoundPlayer
import dagger.Module
import dagger.Provides

@Module
class MetronomeModule {
    @Provides
    fun provideSoundPool(): SoundPool {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        return SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    @Provides
    fun provideSoundPlayer(soundPool: SoundPool, context: Context): Player =
        SoundPlayer(soundPool, soundPool.load(context, R.raw.drumsticks, 1))
}
