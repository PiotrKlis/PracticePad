package com.piotr.practicepad.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piotr.practicepad.data.dao.ExerciseDao
import com.piotr.practicepad.data.dao.ExerciseSetDao
import com.piotr.practicepad.exercise.ExerciseEntity
import com.piotr.practicepad.exerciseList.ExerciseSetEntity

@Database(entities = [ExerciseEntity::class, ExerciseSetEntity::class], version = 1)
abstract class PracticePadRoomDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseSetDao(): ExerciseSetDao
}