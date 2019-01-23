package com.piotr.practicepad.ui.main

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.piotr.practicepad.ui.main.Exercise.external.ExerciseDao
import com.piotr.practicepad.ui.main.Exercise.external.ExerciseData
import com.piotr.practicepad.ui.main.Exercise.external.ExerciseEntity
import com.piotr.practicepad.ui.main.ExerciseList.external.ExerciseSetDao
import com.piotr.practicepad.ui.main.ExerciseList.external.ExerciseSetData
import com.piotr.practicepad.ui.main.ExerciseList.external.ExerciseSetEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [ExerciseSetEntity::class, ExerciseEntity::class], version = 1)
abstract class PracticePadDatabase : RoomDatabase() {

    abstract fun exerciseSetDao(): ExerciseSetDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        private var INSTANCE: PracticePadDatabase? = null

        fun getInstance(): PracticePadDatabase? {
            return INSTANCE
        }

        fun initializeDatabase(application: Application) {
            if (INSTANCE == null) {
                synchronized(PracticePadDatabase::class) {
                    INSTANCE =
                            Room.databaseBuilder(application, PracticePadDatabase::class.java, "exercise_set_database")
                                .fallbackToDestructiveMigration()
                                .addCallback(roomDatabaseCallback())
                                .build()
                }
            }
        }

        private fun roomDatabaseCallback() = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                GlobalScope.launch {
                    insertDataIntoDB()
                }
            }
        }

        private fun insertDataIntoDB() {
            purgeDB()
            saveExercises()
            saveExerciseSets()
        }

        private fun purgeDB() {
            val exerciseSetDao = INSTANCE?.exerciseSetDao()
            val exerciseDao = INSTANCE?.exerciseDao()
            exerciseSetDao?.deleteAll()
            exerciseDao?.deleteAll()
        }

        private fun saveExerciseSets() {
            val exerciseSetDao = INSTANCE?.exerciseSetDao()
            val enumExercises = ExerciseSetData.values()

            for (exerciseSet in enumExercises) {
                exerciseSetDao?.insert(
                    mapExerciseSetDataToEntities(
                        exerciseSet
                    )
                )
            }
        }

        private fun saveExercises() {
            val exerciseDao = INSTANCE?.exerciseDao()
            val enumExercises = ExerciseData.values()

            for (exercise in enumExercises) {
                exerciseDao?.insert(
                    mapExerciseDataToEntities(
                        exercise
                    )
                )
            }
        }

        private fun mapExerciseDataToEntities(exercise: ExerciseData): ExerciseEntity {
            return ExerciseEntity(
                exercise.id,
                exercise.time,
                exercise.title,
                exercise.image
            )
        }

        private fun mapExerciseSetDataToEntities(exerciseSet: ExerciseSetData): ExerciseSetEntity {
            return ExerciseSetEntity(
                exerciseSet.id,
                exerciseSet.title
            )
        }
    }
}
