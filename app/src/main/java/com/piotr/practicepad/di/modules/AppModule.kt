package com.piotr.practicepad.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import com.piotr.practicepad.PracticePad
import com.piotr.practicepad.data.db.ActiveSetSharedPrefs
import com.piotr.practicepad.metronome.Metronome
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: PracticePad): Context = application

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences("active_set", 0)

    @Singleton
    @Provides
    fun provideActiveSetSharedPreferences(sharedPreferences: SharedPreferences) = ActiveSetSharedPrefs(sharedPreferences)
    
}
