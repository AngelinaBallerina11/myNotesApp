package com.angelinaandronova.mobile_ui.injection.module

import com.angelinaandronova.data.repository.NotesRemote
import com.angelinaandronova.mobile_ui.BuildConfig
import com.angelinaandronova.remote.NotesRemoteImpl
import com.angelinaandronova.remote.service.NotesService
import com.angelinaandronova.remote.service.NotesServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideNotesService(): NotesService {
            return NotesServiceFactory.makeNotesService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindNotesRemote(notesRemote: NotesRemoteImpl): NotesRemote
}
