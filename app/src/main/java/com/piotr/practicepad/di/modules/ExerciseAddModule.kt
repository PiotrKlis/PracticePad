package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.views.addExerciseSet.AddExerciseSetFragment
import com.piotr.practicepad.views.addExerciseSet.AddExerciseSetViewModel
import com.piotr.practicepad.views.exerciseSetList.ExerciseSetListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExerciseAddModule {

    @ContributesAndroidInjector
    abstract fun contributesAddExerciseAddFragment(): AddExerciseSetFragment

    @Binds
    @IntoMap
    @ViewModelKey(AddExerciseSetViewModel::class)
    abstract fun bindsAddExerciseSetViewModel(viewModelSet: AddExerciseSetViewModel): ViewModel
}