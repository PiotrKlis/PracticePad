package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.exerciseSetDetail.ExerciseSetDetailViewModel
import com.piotr.practicepad.exerciseSetDetail.ExerciseSetDetailsFragment
import com.piotr.practicepad.exerciseSetDetail.ExerciseSetDetailsStateMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExerciseSetDetailsModule {
    @ContributesAndroidInjector
    abstract fun contributesExerciseSetDetailsFragment(): ExerciseSetDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseSetDetailViewModel::class)
    abstract fun bindsExerciseSetDetailsViewModel(viewModel: ExerciseSetDetailViewModel): ViewModel
}
