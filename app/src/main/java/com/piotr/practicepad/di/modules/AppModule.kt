package com.piotr.practicepad.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.piotr.practicepad.PracticePad
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.entities.ExerciseEntityMapper
import com.piotr.practicepad.data.entities.ExerciseSetEntityMapper
import com.piotr.practicepad.utils.AndroidResourceProvider
import com.piotr.practicepad.utils.ResourceProvider
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
        context.getSharedPreferences(SharedPrefs.KEY_ACTIVE_SET, Context.MODE_PRIVATE)

    @Provides
    fun provideExerciseSetEntityMapper(resourceProvider: ResourceProvider, exerciseEntityMapper: ExerciseEntityMapper) =
        ExerciseSetEntityMapper(resourceProvider, exerciseEntityMapper)

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider =
        AndroidResourceProvider(context)

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): PracticePadRoomDatabase =
        PracticePadRoomDatabase.getInstance(context)
}
