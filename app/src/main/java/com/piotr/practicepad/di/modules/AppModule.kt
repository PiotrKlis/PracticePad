package com.piotr.practicepad.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.piotr.practicepad.PracticePad
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.repository.ExerciseSetEntityMapper
import com.piotr.practicepad.di.utils.DataConverter
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
    fun provideDataConverter() = DataConverter()

    @Provides
    fun provideExerciseSetEntityMapper(dataConverter: DataConverter) = ExerciseSetEntityMapper(dataConverter)

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context, exerciseSetEntityMapper: ExerciseSetEntityMapper): PracticePadRoomDatabase =
        PracticePadRoomDatabase.getInstance(context, exerciseSetEntityMapper)
}
