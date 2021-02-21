package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.views.addExercise.AddExerciseFragment
import com.piotr.practicepad.views.addExercise.AddExerciseSetViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExerciseAddModule {

    @ContributesAndroidInjector
    abstract fun contributesAddExerciseAddFragment(): AddExerciseFragment

    @Binds
    @IntoMap
    @ViewModelKey(AddExerciseSetViewModel::class)
    abstract fun bindsAddExerciseSetViewModel(viewModelSet: AddExerciseSetViewModel): ViewModel
}