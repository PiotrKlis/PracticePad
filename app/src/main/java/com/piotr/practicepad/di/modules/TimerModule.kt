package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.timers.ExerciseSetTimerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TimerModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExerciseSetTimerViewModel::class)
    abstract fun bindsTimerViewModel(viewModelExerciseSet: ExerciseSetTimerViewModel): ViewModel
}