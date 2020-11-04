package com.piotr.practicepad.exerciseList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piotr.practicepad.exercise.Exercise

data class ExerciseSet(
    val id: Int = 0,
    val title: String = "",
    val tempo: Int = 0,
    val exercises: List<Exercise> = listOf()
) {
    fun shouldStartNextExercise(position: Int): Boolean = position < exercises.size
}

@Entity(tableName = "exercise_set_table")
class ExerciseSetEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "tempo") val tempo: Int,
    @ColumnInfo(name = "exercises") val exercises: Array<String>
)
