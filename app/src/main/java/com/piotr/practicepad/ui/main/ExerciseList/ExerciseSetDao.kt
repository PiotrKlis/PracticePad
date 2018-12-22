package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface ExerciseSetDao {
    @Query("SELECT * FROM exerciseset")
    fun getAll(): LiveData<List<ExerciseSet>>

    @Insert(onConflict = REPLACE)
    fun insert(exerciseSet: ExerciseSet)

    @Query("DELETE from exerciseset")
    fun deleteAll()

}