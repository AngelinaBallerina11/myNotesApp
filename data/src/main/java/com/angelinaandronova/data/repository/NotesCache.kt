package com.angelinaandronova.data.repository

import com.angelinaandronova.data.model.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


interface NotesCache {

    fun clearNotes(): Completable

    fun saveNotes(notes: List<NoteEntity>): Completable

    fun getNotes(): Observable<List<NoteEntity>>

    fun areNotesCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isNotesCacheExpired(): Single<Boolean>

    fun getNote(id: Long): Observable<NoteEntity>
    fun editNote(note: NoteEntity): Observable<NoteEntity>
    fun createNote(note: NoteEntity): Observable<NoteEntity>
    fun deleteNote(id: Long): Completable

}