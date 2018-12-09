package com.angelinaandronova.data.repository

import com.angelinaandronova.data.model.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable


interface NotesDataStore {

    fun clearNotes(): Completable
    fun saveNotes(notes: List<NoteEntity>): Completable
    fun getNotes(): Observable<List<NoteEntity>>
    fun getNote(id: Long): Observable<NoteEntity>
    fun editNote(note: NoteEntity): Observable<NoteEntity>
    fun createNote(note: NoteEntity): Observable<NoteEntity>
    fun deleteNote(id: Long): Completable
}