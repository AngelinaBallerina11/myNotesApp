package com.angelinaandronova.data.repository

import com.angelinaandronova.data.model.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable


interface NotesRemote {

    fun getNotes(): Observable<List<NoteEntity>>
    fun getNote(id: Long): Observable<NoteEntity>
    fun editNote(note: NoteEntity): Completable
    fun createNoteRemote(note: NoteEntity): Observable<NoteEntity>
    fun deleteNote(id: Long): Completable
}