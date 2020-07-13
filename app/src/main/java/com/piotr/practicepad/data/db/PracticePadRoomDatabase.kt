package com.piotr.practicepad.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.piotr.practicepad.data.dao.ExerciseSetDao
import com.piotr.practicepad.data.repository.ExerciseSetEntityMapper
import com.piotr.practicepad.exerciseList.ExerciseSetEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [ExerciseSetEntity::class], version = 1)
abstract class PracticePadRoomDatabase :
    RoomDatabase() {
    abstract fun exerciseSetDao(): ExerciseSetDao

    companion object {
        @Volatile
        private var INSTANCE: PracticePadRoomDatabase? = null

        fun getInstance(
            context: Context,
            exerciseSetEntityMapper: ExerciseSetEntityMapper
        ): PracticePadRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context, exerciseSetEntityMapper).also { INSTANCE = it }
            }

        fun buildDatabase(context: Context, exerciseSetEntityMapper: ExerciseSetEntityMapper) =
            Room.databaseBuilder(
                context,
                PracticePadRoomDatabase::class.java,
                "practice_pad_database"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch(Dispatchers.Main) {
                            INSTANCE?.apply {
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
