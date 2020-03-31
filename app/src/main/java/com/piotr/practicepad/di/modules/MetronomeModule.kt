package com.piotr.practicepad.di.modules

import android.media.MediaPlayer
import com.piotr.practicepad.metronome.Metronome
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MetronomeModule {
    @Singleton
    @Provides
    fun provideMetronome(mediaPlayer: MediaPlayer) = Metronome(mediaPlayer)
}