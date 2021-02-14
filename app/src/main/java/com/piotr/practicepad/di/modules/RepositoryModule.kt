package com.piotr.practicepad.di.modules

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.entities.ExerciseSetEntityMapper
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(
        sharedPrefs: SharedPrefs,
        database: PracticePadRoomDatabase,
        exerciseSetEntityMapper: ExerciseSetEntityMapper
    ) = ExerciseSetRepository(sharedPrefs, database, exerciseSetEntityMapper)
}