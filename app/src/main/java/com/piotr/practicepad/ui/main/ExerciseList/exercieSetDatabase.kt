package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.piotr.practicepad.ui.main.Exercise.Exercise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [ExerciseSet::class], version = 1)
abstract class ExercieSetDatabase : RoomDatabase() {

    abstract fun exerciseSetDao(): ExerciseSetDao

    companion object {
        private var INSTANCE: ExercieSetDatabase? = null
        fun getInstance(context: Context): ExercieSetDatabase? {
            if (INSTANCE == null) {
                synchronized(ExercieSetDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, ExercieSetDatabase::class.java, "exercise_set_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomDatabaseCallback())
                        .build()
                }
            }
            return INSTANCE
        }

        private fun roomDatabaseCallback() = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                GlobalScope.launch {
                    val exerciseSetDao = INSTANCE?.exerciseSetDao()
                    val exerciseSets = getExerciseSets()
                }
            }
        }

        private fun getExerciseSets(): List<ExerciseSet> {
            val firstExerciseSet = ExerciseSet(1, getFirstExerciseSetList(), "First Exercise Set")
            val secondExerciseSet = ExerciseSet(2, getSecondExerciseSetList(), "Second Exercise Set")
            val thirdExerciseSet = ExerciseSet(3, getThirdExerciseSetList(), "Third Exercise Set")
            return listOf(firstExerciseSet, secondExerciseSet, thirdExerciseSet)
        }

        private fun getThirdExerciseSetList(): List<Exercise> {
            return listOf(Exercise.SINGLE_STROKE_FOUR, Exercise.DOUBLES, Exercise.SINGLE_STROKE_SEVEN)
        }

        private fun getSecondExerciseSetList(): List<Exercise> {
            return listOf(Exercise.SEVEN_STROKE_ROLL, Exercise.TRIPLETS, Exercise.SIX_STROKE_ROLL)
        }

        private fun getFirstExerciseSetList(): List<Exercise> {
            return listOf(Exercise.DOUBLES, Exercise.DOUBLE_STROKE_OPEN_ROLL, Exercise.FIVE_STROKE_ROLL)
        }
    }

}