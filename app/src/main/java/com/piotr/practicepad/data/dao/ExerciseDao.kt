package com.piotr.practicepad.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotr.practicepad.data.entities.ExerciseEntity

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<ExerciseEntity>)
    @Query("SELECT * FROM exercise_table")
    suspend fun getAll(): List<ExerciseEntity>
    @Query("SELECT * FROM exercise_table WHERE title LIKE :query")
    suspend fun getExerciseForText(query: String?): List<ExerciseEntity>
    @Query("SELECT * FROM exercise_table WHERE id IS :exerciseId")
    suspend fun getExerciseForId(exerciseId: Int): ExerciseEntity
}
