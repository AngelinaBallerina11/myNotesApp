package com.angelinaandronova.mobile_ui.injection.module

import android.app.Application
import com.angelinaandronova.cache.NotesCacheImpl
import com.angelinaandronova.cache.db.NotesDatabase
import com.angelinaandronova.data.repository.NotesCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): NotesDatabase {
            return NotesDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: NotesCacheImpl): NotesCache
}
