package com.piotr.practicepad.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.piotr.practicepad.data.dao.ExerciseSetDao
import com.piotr.practicepad.data.repository.ExerciseSetEntityMapper
import com.piotr.practicepad.exerciseList.ExerciseSetEntity
import javax.inject.Inject

@Database(entities = [ExerciseSetEntity::class], version = 1)
abstract class PracticePadRoomDatabase : RoomDatabase() {
    abstract fun exerciseSetDao(): ExerciseSetDao
    @Inject
    lateinit var exerciseSetEntityMapper: ExerciseSetEntityMapper

    companion object {
        @Volatile
        private var INSTANCE: PracticePadRoomDatabase? = null

        fun getInstance(context: Context): PracticePadRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                PracticePadRoomDatabase::class.java,
                "practice_pad_database"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        suspend {
                            getInstance(context).apply {
                                exerciseSetDao().insertAll(
                                    exerciseSetEntityMapper.map(
                                        ExerciseSetData.values()
                                    )
                                )
                            }
                        }
                    }
                })
                .build()
    }
}
