package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.views.exerciseSet.ExerciseSetViewModel
import com.piotr.practicepad.views.exerciseSet.ExerciseSetFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Module
abstract class ExerciseSetDetailsModule {
    @ContributesAndroidInjector
    abstract fun contributesExerciseSetDetailsFragment(): ExerciseSetFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseSetViewModel::class)
    abstract fun bindsExerciseSetDetailsViewModel(viewModel: ExerciseSetViewModel): ViewModel
}
