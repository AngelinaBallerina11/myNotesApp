package com.angelinaandronova.data

import com.angelinaandronova.data.mapper.NoteMapper
import com.angelinaandronova.data.repository.NotesCache
import com.angelinaandronova.data.store.NotesDataStoreFactory
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.domain.repository.NotesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject


class NotesDataRepository @Inject constructor(
    private val mapper: NoteMapper,
    private val factory: NotesDataStoreFactory,
    private val cache: NotesCache
) : NotesRepository {
    override fun getNotes(): Observable<List<Note>> {
        return observableCacheState()
            .flatMap { factory.getDataStore(it.first, it.second).getNotes() }
            .flatMap { notes ->
                factory.getCacheDataStore()
                    .saveNotes(notes)
                    .andThen(Observable.just(notes))
            }
            .map { list ->
                list.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun getNote(id: Long): Observable<Note> {
        return observableCacheState()
            .flatMap { factory.getDataStore(it.first, it.second).getNote(id) }
            .map { mapper.mapFromEntity(it) }
    }

    private fun observableCacheState(): Observable<Pair<Boolean, Boolean>> {
        return Observable.zip(cache.areNotesCached().toObservable(),
            cache.isNotesCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired -> Pair(areCached, isExpired) })
    }

    override fun editNote(note: Note): Completable {
        return Observable.just(note)
            .map { mapper.mapToEntity(it) }
            .flatMapCompletable { noteEntity ->
                factory.getCacheDataStore().editNote(noteEntity)
                    .andThen { factory.getRemoteDataStore().editNote(noteEntity) }
            }
    }

    override fun createNote(note: Note): Completable {
        return Observable.just(note)
            .map { mapper.mapToEntity(it) }
            .flatMapCompletable { noteEntity ->
                factory.getCacheDataStore().createNote(noteEntity)
                    .andThen { factory.getRemoteDataStore().createNote(noteEntity) }
            }
    }

    override fun deleteNote(id: Long): Observable<Long> {
        return Observable.just(id)
            .flatMap(factory.getCacheDataStore()::deleteNote)
            .flatMap(factory.getRemoteDataStore()::deleteNote)
    }
}