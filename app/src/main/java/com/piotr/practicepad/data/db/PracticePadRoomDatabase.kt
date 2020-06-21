package com.piotr.practicepad.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.piotr.practicepad.data.dao.ExerciseSetDao
import com.piotr.practicepad.data.repository.ExerciseSetEntityMapper
import com.piotr.practicepad.exerciseList.ExerciseSetEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Database(entities = [ExerciseSetEntity::class], version = 1)
abstract class PracticePadRoomDatabase @Inject constructor(private val exerciseSetEntityMapper: ExerciseSetEntityMapper) :
    RoomDatabase() {
    abstract fun exerciseSetDao(): ExerciseSetDao

    companion object {
        @Volatile
        private var INSTANCE: PracticePadRoomDatabase? = null

        fun getInstance(context: Context): PracticePadRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                PracticePadRoomDatabase::class.java,
                "practice_pad_database"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        Log.d("AAA db", "before super")
                        super.onCreate(db)

                        GlobalScope.launch() {
                            INSTANCE?.apply {
                                Log.d(
                                    "AAA db launch", exerciseSetEntityMapper.map(
                                        ExerciseSetData.values()
                                    ).toString()
                                )
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

/*Room.databaseBuilder(context.applicationContext,
        DataDatabase::class.java, "Sample.db")
        // prepopulate the database after onCreate was called
        .addCallback(object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // moving to a new thread
                ioThread {
                    getInstance(context).dataDao()
                                        .insert(PREPOPULATE_DATA)
                }
            }
        })
        .build()*/