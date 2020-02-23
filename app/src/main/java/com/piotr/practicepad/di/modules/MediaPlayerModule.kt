package com.piotr.practicepad.di.modules

import android.content.Context
import android.media.MediaPlayer
import com.piotr.practicepad.R
import dagger.Module
import dagger.Provides

@Module
class MediaPlayerModule {
    @Provides
    fun provideMediaPlayer(context: Context): MediaPlayer =
        MediaPlayer.create(context, R.raw.low_seiko_sq50)
}