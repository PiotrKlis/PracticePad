package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.views.exerciseSetList.ExerciseSetListFragment
import com.piotr.practicepad.views.exerciseSetList.ExerciseSetListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExerciseListModule {
    @ContributesAndroidInjector
    abstract fun contributesExerciseListFragment(): ExerciseSetListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseSetListViewModel::class)
    abstract fun bindsExerciseSetViewModel(viewModelSet: ExerciseSetListViewModel): ViewModel

}