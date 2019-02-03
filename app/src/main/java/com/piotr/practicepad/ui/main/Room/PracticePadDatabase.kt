package com.piotr.practicepad.ui.main.Room

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import com.piotr.practicepad.ui.main.Exercise.ExerciseData
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [ExerciseSetEntity::class, ExerciseEntity::class], version = 2)
@TypeConverters(ListConverter::class)
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
            val exerciseSetEntityList: ArrayList<ExerciseSetEntity>? = arrayListOf()

            for (exerciseSet in enumExercises) {
                val exerciseSetEntity =
                    mapExerciseSetDataToEntities(
                        exerciseSet
                    )
                exerciseSetEntityList?.add(exerciseSetEntity)
            }
            exerciseSetDao?.insert(exerciseSetEntityList)
        }

        private fun saveExercises() {
            val exerciseDao = INSTANCE?.exerciseDao()
            val enumExercises = ExerciseData.values()
            val exerciseEntityList: ArrayList<ExerciseEntity>? = arrayListOf()

            for (exercise in enumExercises) {
                val exerciseEntity =
                    mapExerciseDataToEntities(exercise)
                exerciseEntityList?.add(exerciseEntity)
            }
            exerciseDao?.insert(exerciseEntityList)
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
