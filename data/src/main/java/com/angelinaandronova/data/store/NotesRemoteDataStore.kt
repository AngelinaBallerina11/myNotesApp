package com.angelinaandronova.data.store

import com.angelinaandronova.data.model.NoteEntity
import com.angelinaandronova.data.repository.NotesDataStore
import com.angelinaandronova.data.repository.NotesRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject


class NotesRemoteDataStore @Inject constructor(private val remote: NotesRemote) : NotesDataStore {
    override fun clearNotes(): Completable {
        throw UnsupportedOperationException("Clearing notes is not supported")
    }

    override fun saveNotes(notes: List<NoteEntity>): Completable {
        throw UnsupportedOperationException("Saving notes is not supported")
    }

    override fun getNotes(): Observable<List<NoteEntity>> {
        return remote.getNotes()
    }

    override fun getNote(id: Long): Observable<NoteEntity> {
        return remote.getNote(id)
    }

    override fun editNote(note: NoteEntity): Completable {
        return remote.editNote(note)
    }

    override fun createNote(note: NoteEntity): Observable<Long> {
        return remote.createNote(note)
    }

    override fun deleteNote(id: Long): Completable {
        return remote.deleteNote(id)
    }
}