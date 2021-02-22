package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.di.utils.ViewModelKey
import com.piotr.practicepad.views.addExercise.AddExerciseFragment
import com.piotr.practicepad.views.addExercise.AddExerciseViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AddExerciseModule {
    @ContributesAndroidInjector
    abstract fun contributesAddExerciseFragment(): AddExerciseFragment

    @Binds
    @IntoMap
    @ViewModelKey(AddExerciseViewModel::class)
    abstract fun bindsAddExerciseViewModel(viewModel: AddExerciseViewModel): ViewModel

}