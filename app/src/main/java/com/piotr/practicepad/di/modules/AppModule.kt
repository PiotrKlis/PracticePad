package com.piotr.practicepad.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.piotr.practicepad.PracticePad
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.repository.AndroidResourceProvider
import com.piotr.practicepad.data.repository.ExerciseSetEntityMapper
import com.piotr.practicepad.data.repository.ResourceProvider
import com.piotr.practicepad.exerciseSetDetail.ExerciseSetDetailsStateMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: PracticePad): Context = application

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("active_set", 0)

    @Singleton
    @Provides
    fun provideActiveSetSharedPreferences(sharedPreferences: SharedPreferences) =
        SharedPrefs(sharedPreferences)

    //TODO: Move it from here to some different module
    @Provides
    fun provideExerciseSetDetailsMapper() = ExerciseSetDetailsStateMapper()

    @Provides
    fun provideExerciseSetEntityMapper(resourceProvider: ResourceProvider) = ExerciseSetEntityMapper(resourceProvider)

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider = AndroidResourceProvider(context)

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): PracticePadRoomDatabase =
        PracticePadRoomDatabase.getInstance(context)
}
