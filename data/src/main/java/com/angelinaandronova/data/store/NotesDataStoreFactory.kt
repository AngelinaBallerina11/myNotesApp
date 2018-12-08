package com.angelinaandronova.data.store

import com.angelinaandronova.data.repository.NotesDataStore
import javax.inject.Inject

/*
* Decides which data store is used
* */
open class NotesDataStoreFactory @Inject constructor(
    private val cacheDataStore: NotesCacheDataStore,
    private val remoteDataStore: NotesRemoteDataStore
) {

    open fun getDataStore(
        notesCached: Boolean,
        cacheExpired: Boolean
    ): NotesDataStore {

        return if (notesCached && !cacheExpired) {
            cacheDataStore
        } else {
            remoteDataStore
        }
    }

    fun getCacheDataStore(): NotesCacheDataStore = cacheDataStore
    fun getRemoteDataStore(): NotesRemoteDataStore = remoteDataStore
}