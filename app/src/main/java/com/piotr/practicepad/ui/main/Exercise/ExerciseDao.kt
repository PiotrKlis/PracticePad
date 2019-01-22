package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exerciseentity")
    fun getAll(): LiveData<List<ExerciseEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(exerciseEntity: ExerciseEntity)

    @Query("DELETE from exerciseentity")
    fun deleteAll()

    @Query("SELECT * FROM exerciseentity WHERE id = :exerciseSetId")
    fun getExercisesById(exerciseSetId: Int): LiveData<List<ExerciseEntity>>
}
