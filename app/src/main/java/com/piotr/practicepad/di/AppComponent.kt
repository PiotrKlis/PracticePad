package com.piotr.practicepad.di

import com.piotr.practicepad.PracticePad
import com.piotr.practicepad.di.modules.ActivityBindingModule
import com.piotr.practicepad.di.modules.AppModule
import com.piotr.practicepad.di.modules.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<PracticePad> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<PracticePad>
}


