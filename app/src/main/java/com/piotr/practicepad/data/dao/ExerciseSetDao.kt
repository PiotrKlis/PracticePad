package com.piotr.practicepad.data.dao

import androidx.room.*
import com.piotr.practicepad.data.entities.ExerciseEntity
import com.piotr.practicepad.data.entities.ExerciseSetEntity

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

    @Update(entity = ExerciseSetEntity::class)
    suspend fun update(exerciseSetEntityUpdate: ExerciseSetEntityUpdate)
}

@Entity
class ExerciseSetEntityUpdate(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "exercises")
    val exercises: List<ExerciseEntity>
)
