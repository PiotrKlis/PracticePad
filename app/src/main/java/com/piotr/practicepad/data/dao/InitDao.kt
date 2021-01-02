package com.piotr.practicepad.data.dao

import androidx.room.*
import com.piotr.practicepad.data.entities.ExerciseEntity
import com.piotr.practicepad.data.entities.ExerciseSetEntity

//@Dao
//interface InitDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun init(init: InitEntity)
//}
//
//@Entity(tableName = "init")
//class InitEntity(
//    @PrimaryKey val value: String
//)