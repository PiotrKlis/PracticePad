package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.exerciseList.ExerciseListFragment
import com.piotr.practicepad.exerciseList.ExerciseSetViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExerciseListModule {
    @ContributesAndroidInjector
    abstract fun contributesExerciseListFragment(): ExerciseListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseSetViewModel::class)
    abstract fun bindsExerciseSetViewModel(viewModel: ExerciseSetViewModel): ViewModel

}