package com.piotr.practicepad.di.modules

import com.piotr.practicepad.views.exercise.Practice
import dagger.Module
import dagger.Provides

@Module
class PracticeStateModule  {

    @Provides
    fun providesPracticeState(): Practice = Practice()
}