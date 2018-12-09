package com.angelinaandronova.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.angelinaandronova.cache.dao.ConfigDao
import com.angelinaandronova.cache.dao.NotesDao
import com.angelinaandronova.cache.db.Constants.Db.DB_NAME
import com.angelinaandronova.cache.model.CachedNote
import com.angelinaandronova.cache.model.Config
import javax.inject.Inject

@Database(
    entities = arrayOf(
        CachedNote::class,
        Config::class
    ),
    version = 1
)
abstract class NotesDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedProjectsDao(): NotesDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: NotesDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): NotesDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NotesDatabase::class.java,
                            DB_NAME
                        )
                            .build()
                    }
                    return INSTANCE as NotesDatabase
                }
            }
            return INSTANCE as NotesDatabase
        }
    }

}