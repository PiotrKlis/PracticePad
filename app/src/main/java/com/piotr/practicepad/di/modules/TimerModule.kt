package com.piotr.practicepad.di.modules

import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.timers.ExerciseSetTimer
import com.piotr.practicepad.timers.ExerciseTimer
import dagger.Module
import dagger.Provides

@Module
class TimerModule {

    @Provides
    fun bindsExerciseSetTimer(exerciseSetRepository: ExerciseSetRepository): ExerciseSetTimer =
        ExerciseSetTimer(exerciseSetRepository)

    @Provides
    fun bindsExerciseTimer(exerciseSetRepository: ExerciseSetRepository): ExerciseTimer =
        ExerciseTimer(exerciseSetRepository)
}
