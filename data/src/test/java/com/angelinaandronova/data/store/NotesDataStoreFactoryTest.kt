package com.angelinaandronova.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

class NotesDataStoreFactoryTest {

    private val cacheStore = mock<NotesCacheDataStore>()
    private val remoteStore = mock<NotesRemoteDataStore>()
    private val factory = NotesDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun `factory returns remote data store when cache is expired`() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun `factory returns remote when there is no cache available`() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun factoryReturnsCacheWhenAvailableAndNotExpired() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCacheDataStoreReturnCacheDataStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }

    @Test
    fun getRemoteDataStoreReturnRemoteDataStore() {
        assertEquals(remoteStore, factory.getRemoteDataStore())
    }
}