package com.angelinaandronova.data.store

import com.angelinaandronova.data.model.NoteEntity
import com.angelinaandronova.data.repository.NotesCache
import com.angelinaandronova.data.repository.NotesDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject


class NotesCacheDataStore @Inject constructor(private val notesCache: NotesCache) : NotesDataStore {
    override fun createNoteRemote(note: NoteEntity): Observable<NoteEntity> {
        throw UnsupportedOperationException("Remote method - not implemented in cache")
    }

    override fun clearNotes(): Completable = notesCache.clearNotes()

    override fun saveNotes(notes: List<NoteEntity>): Completable =
        notesCache.saveNotes(notes)
            .andThen(notesCache.setLastCacheTime(System.currentTimeMillis()))

    override fun getNotes(): Observable<List<NoteEntity>> = notesCache.getNotes()

    override fun getNote(id: Long): Observable<NoteEntity> = notesCache.getNote(id)

    override fun editNote(note: NoteEntity): Completable = notesCache.editNote(note)

    override fun createNote(note: NoteEntity): Observable<Long> = notesCache.createNote(note)

    override fun deleteNote(id: Long): Completable = notesCache.deleteNote(id)
}