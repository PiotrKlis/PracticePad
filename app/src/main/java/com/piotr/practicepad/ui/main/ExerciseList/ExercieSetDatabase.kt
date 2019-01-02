package com.piotr.practicepad.ui.main.ExerciseList

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.Exercise.ExerciseDao
import com.piotr.practicepad.ui.main.Exercise.ExerciseEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [ExerciseSetEntity::class, ExerciseEntity::class], version = 1)
abstract class ExercieSetDatabase : RoomDatabase() {

    abstract fun exerciseSetDao(): ExerciseSetDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        private var INSTANCE: ExercieSetDatabase? = null

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

        fun getInstance(): ExercieSetDatabase? {
            return INSTANCE
        }

        private fun roomDatabaseCallback() = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                GlobalScope.launch {
                    insertDataIntoDB()
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
               exerciseSetDao?.insert(getExerciseSets())
            }

            private fun getExerciseSets(): ExerciseSetEntity {
                val domainExercises: MutableList<Exercise> = Collections.emptyList()
                val enumExercises = ExerciseData.values()

                for (exercise in enumExercises) {
                    domainExercises.add(mapToDomain(exercise))
                }
                return domainExercises
            }

            private fun saveExercises() {
                val exerciseDao = INSTANCE?.exerciseDao()
                exerciseDao?.insertList(getExercises())
            }
        }

        private fun getExerciseSets(): List<ExerciseSetEntity> {
            val firstExerciseSet = ExerciseSetEntity(1, "First ExerciseEntity Set")
            val secondExerciseSet = ExerciseSetEntity(2, "Second ExerciseEntity Set")
            val thirdExerciseSet = ExerciseSetEntity(3, "Third ExerciseEntity Set")
            return listOf(firstExerciseSet, secondExerciseSet, thirdExerciseSet)
        }

        private fun getExercises(): MutableList<> {
            val domainExercises: MutableList<Exercise> = Collections.emptyList()
            val enumExercises = ExerciseData.values()

            for (exercise in enumExercises) {
                domainExercises.add(mapToDomain(exercise))
            }
            return domainExercises
        }

        private fun mapToDomain(exercise: ExerciseData): Exercise {
            return Exercise(exercise.id, exercise.time, exercise.title, exercise.image)
        }
    }
}
