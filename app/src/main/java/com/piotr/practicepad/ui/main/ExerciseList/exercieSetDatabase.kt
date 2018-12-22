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
                    INSTANCE = Room.databaseBuilder(context, ExercieSetDatabase::class.java, "exercise_set_databse")
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

        private fun getExerciseSets() {
            val paradiddle = Exercise(120, "Paradiddle", "paradiddle", 1)
            val doubles = Exercise(90, "Doubles", "doubles", 2)
            val triplets = Exercise(60, "Triplets", "triplets", 3)
        }
    }

}