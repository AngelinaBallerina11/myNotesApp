package com.angelinaandronova.domain.repository

import com.angelinaandronova.domain.model.Note
import io.reactivex.Completable
import io.reactivex.Observable

/*
*   Abstraction for Use Cases
*   Implemented in Data layer
* */
interface NotesRepository {

    fun getNotes(): Observable<List<Note>>
    fun getNote(id: Long): Observable<Note>
    fun editNote(note: Note): Completable
    fun createNote(note: Note): Observable<Long>
    fun deleteNote(id: Long): Completable
}