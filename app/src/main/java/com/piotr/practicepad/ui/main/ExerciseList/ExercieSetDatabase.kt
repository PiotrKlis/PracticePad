package com.piotr.practicepad.ui.main.ExerciseList

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.piotr.practicepad.ui.main.Exercise.ExerciseDao
import com.piotr.practicepad.ui.main.Exercise.ExerciseEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [ExerciseSetEntity::class, ExerciseEntity::class], version = 1)
abstract class ExercieSetDatabase : RoomDatabase() {

    abstract fun exerciseSetDao(): ExerciseSetDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        private var INSTANCE: ExercieSetDatabase? = null

        fun getInstance(): ExercieSetDatabase? {
            return INSTANCE
        }

        fun initializeDatabase(application: Application) {
            if (INSTANCE == null) {
                synchronized(ExercieSetDatabase::class) {
                    INSTANCE =
                            Room.databaseBuilder(application, ExercieSetDatabase::class.java, "exercise_set_database")
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
                exerciseSetDao?.insert(mapExerciseSetDataToEntities(exerciseSet))
            }
        }

        private fun saveExercises() {
            val exerciseDao = INSTANCE?.exerciseDao()
            val enumExercises = ExerciseData.values()

            for (exercise in enumExercises) {
                exerciseDao?.insert(mapExerciseDataToEntities(exercise))
            }
        }

        private fun mapExerciseDataToEntities(exercise: ExerciseData): ExerciseEntity {
            return ExerciseEntity(exercise.id, exercise.time, exercise.title, exercise.image)
        }

        private fun mapExerciseSetDataToEntities(exerciseSet: ExerciseSetData): ExerciseSetEntity {
            return ExerciseSetEntity(exerciseSet.id, exerciseSet.title)
        }
    }
}
