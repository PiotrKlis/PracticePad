package com.piotr.practicepad.data.dao

import androidx.room.*
import com.piotr.practicepad.data.entities.ExerciseEntity
import com.piotr.practicepad.data.entities.ExerciseSetEntity

@Dao
interface ExerciseSetDao {
    @Query("SELECT * FROM exercise_set_table")
    suspend fun getAll(): List<ExerciseSetEntity>

    @Query("SELECT * FROM exercise_set_table WHERE id == :setId")
    suspend fun getSetFor(setId: Int): ExerciseSetEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<ExerciseSetEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseSetEntity: ExerciseSetEntity)

    @Query("DELETE FROM exercise_set_table")
    suspend fun deleteAll()

    @Update(entity = ExerciseSetEntity::class)
    suspend fun updateExerciseList(exerciseSetEntityUpdate: UpdateExerciseListEntity)

    @Update(entity = ExerciseSetEntity::class)
    suspend fun updateExerciseSetTitle(exerciseSetEntityUpdate: UpdateExerciseSetTitleEntity)

    @Update(entity = ExerciseSetEntity::class)
    suspend fun updateExerciseSetTempo(exerciseSetEntityUpdate: UpdateExerciseSetTempoEntity)

    @Query("DELETE FROM exercise_set_table WHERE id == :setId")
    suspend fun deleteSet(setId: Int)
}

@Entity
class UpdateExerciseListEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "exercises")
    val exercises: List<ExerciseEntity>
)

@Entity
class UpdateExerciseSetTitleEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String
)

@Entity
class UpdateExerciseSetTempoEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "tempo")
    val tempo: Int
)

@Entity
class UpdateExerciseTimeInExerciseSet(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "time")
    val time: Int
)
