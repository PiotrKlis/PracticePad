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
abstract class AddExerciseSetModule {
    @ContributesAndroidInjector
    abstract fun contributesAddExerciseSetFragment(): AddExerciseFragment

    @Binds
    @IntoMap
    @ViewModelKey(AddExerciseSetViewModel::class)
    abstract fun bindsExerciseViewModel(viewModel: AddExerciseSetViewModel): ViewModel
}