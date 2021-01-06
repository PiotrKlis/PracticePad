package com.piotr.practicepad.di.modules

import android.content.Context
import android.media.MediaPlayer
import com.piotr.practicepad.R
import com.piotr.practicepad.metronome.Metronome
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MetronomeModule {
    @Provides
    fun provideMetronome(mediaPlayer: MediaPlayer): Metronome = Metronome(mediaPlayer)

    @Provides
    fun provideMediaPlayer(context: Context): MediaPlayer =
        MediaPlayer.create(context, R.raw.drumsticks)
}
