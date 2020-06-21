package com.piotr.practicepad.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotr.practicepad.exerciseList.ExerciseSetEntity

@Dao
interface ExerciseSetDao {
    @Query("SELECT * FROM exercise_set_table")
    suspend fun getAll(): List<ExerciseSetEntity>

    @Query("SELECT * FROM exercise_set_table WHERE id == :activeSetId")
    suspend fun getSetFor(activeSetId: Int): ExerciseSetEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<ExerciseSetEntity>)

    @Query("DELETE FROM exercise_set_table")
    suspend fun deleteAll()
}
