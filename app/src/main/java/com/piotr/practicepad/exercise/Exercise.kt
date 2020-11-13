package com.piotr.practicepad.exercise

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piotr.practicepad.R

data class Exercise(
    val id: Int = 0,
    val time: Long = 0L,
    val title: String = "",
    val image: Int = R.drawable.empty
)

@Entity(tableName = "exercise_table")
class ExerciseEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "time") val time: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String
)

