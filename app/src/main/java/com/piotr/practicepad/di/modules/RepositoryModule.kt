package com.piotr.practicepad.di.modules

import com.piotr.practicepad.data.db.ActiveSetSharedPrefs
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(activeSetSharedPrefs: ActiveSetSharedPrefs) = ExerciseSetRepository(activeSetSharedPrefs)
}