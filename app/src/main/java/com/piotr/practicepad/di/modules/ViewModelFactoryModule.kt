package com.piotr.practicepad.di.modules

import androidx.lifecycle.ViewModelProvider
import com.piotr.practicepad.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}