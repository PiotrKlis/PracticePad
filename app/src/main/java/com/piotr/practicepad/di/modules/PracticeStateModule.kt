package com.piotr.practicepad.di.modules

import com.piotr.practicepad.exercise.PracticeState
import dagger.Module
import dagger.Provides

@Module
class PracticeStateModule  {

    @Provides
    fun providesPracticeState(): PracticeState = PracticeState()
}