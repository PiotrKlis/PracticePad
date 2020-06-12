package com.piotr.practicepad.exerciseList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piotr.practicepad.exercise.Exercise

data class ExerciseSet(
    val id: Int = 0,
    val name: String = "",
    val exerciseList: List<Exercise> = listOf(),
    val tempo: Int = 0
) {
    fun shouldStartNextExercise(position: Int): Boolean = position < exerciseList.size
}

@Entity(tableName = "exercise_set_table")
class ExerciseSetEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "exerciseList") val exerciseList: String,
    @ColumnInfo(name = "tempo") val tempo: Int
)
