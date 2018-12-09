package com.angelinaandronova.cache

import com.angelinaandronova.cache.db.NotesDatabase
import com.angelinaandronova.cache.mapper.NotesCacheMapper
import com.angelinaandronova.cache.model.Config
import com.angelinaandronova.data.model.NoteEntity
import com.angelinaandronova.data.repository.NotesCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class NotesCacheImpl @Inject
constructor(
    private val notesDatabase: NotesDatabase,
    private val mapper: NotesCacheMapper
) : NotesCache {

    override fun clearNotes(): Completable {
        return Completable.defer {
            notesDatabase
                .cachedNotesDao()
                .clearNotes()
            Completable.complete()
        }
    }

    override fun saveNotes(notes: List<NoteEntity>): Completable {
        return Completable.defer {
            notesDatabase
                .cachedNotesDao()
                .saveNotes(
                    notes.map { mapper.mapToCached(it) }
                )
            Completable.complete()
        }
    }

    override fun getNotes(): Observable<List<NoteEntity>> {
        return notesDatabase
            .cachedNotesDao()
            .getNotes()
            .map { list ->
                list.map { mapper.mapFromCached(it) }
            }
    }

    override fun areNotesCached(): Single<Boolean> {
        return notesDatabase
            .cachedNotesDao()
            .getNotes()
            .isEmpty
            .map { !it }
    }

    override fun isNotesCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return notesDatabase
            .configDao()
            .getConfig()
            .onErrorReturn { Config(lastCacheTime = 0) }
            .map {
                currentTime - it.lastCacheTime > expirationTime
            }
    }

    override fun getNote(id: Long): Observable<NoteEntity> {
        return notesDatabase
            .cachedNotesDao()
            .getNote(id)
            .map {
                mapper.mapFromCached(it)
            }
    }

    override fun editNote(note: NoteEntity): Completable {
        return Completable.defer {
            notesDatabase
                .cachedNotesDao()
                .editNote(mapper.mapToCached(note))
            Completable.complete()
        }
    }

    override fun createNote(note: NoteEntity): Observable<Long> {
        val createdNoteId = notesDatabase
            .cachedNotesDao()
            .createNote(mapper.mapToCached(note))
        return Observable.just(createdNoteId)
    }

    override fun deleteNote(id: Long): Completable {
        return Completable.defer {
            notesDatabase
                .cachedNotesDao()
                .deleteNote(id)
            Completable.complete()
        }
    }


    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            notesDatabase
                .configDao()
                .insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

}