package com.piotr.practicepad.di.modules

import android.content.Context
import com.piotr.practicepad.PracticePad
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: PracticePad): Context = application
}