package com.piotr.practicepad.exerciseList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piotr.practicepad.exercise.Exercise

data class ExerciseSet(
    val id: Int = 0,
    val title: String = "",
    val tempo: Int = 0,
    val exerciseList: List<Exercise> = listOf()
) {
    fun shouldStartNextExercise(position: Int): Boolean = position < exerciseList.size
}

@Entity(tableName = "exercise_set_table")
class ExerciseSetEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val name: String,
    @ColumnInfo(name = "tempo") val tempo: Int,
    @ColumnInfo(name = "exercises") val exerciseList: String
)
