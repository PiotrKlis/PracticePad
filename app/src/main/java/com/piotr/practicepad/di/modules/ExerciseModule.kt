package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.exercise.ExerciseFragment
import com.piotr.practicepad.exercise.ExerciseViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExerciseModule {
    @ContributesAndroidInjector
    abstract fun contributesExerciseFragment(): ExerciseFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseViewModel::class)
    abstract fun bindsExerciseViewModel(viewModel: ExerciseViewModel): ViewModel
}