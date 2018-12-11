package com.angelinaandronova.remote.service

import com.angelinaandronova.remote.model.NoteModel
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/*
* GET /notes
* POST /notes
* GET /notes/{id}
* PUT /notes/{id}
* DELETE /notes/{id}
*
* */

interface NotesService {

    companion object {
        const val NOTES_ENDPOINT = "/notes"
    }

    @GET(NOTES_ENDPOINT)
    fun getNotes(): Observable<List<NoteModel>>

    @POST(NOTES_ENDPOINT)
    fun createNote(@Body note: NoteModel): Observable<NoteModel>

    @GET("$NOTES_ENDPOINT/{id}")
    fun retrieveNote(id: Long): Observable<NoteModel>

    @PUT("$NOTES_ENDPOINT/{id}")
    fun updateNote(id: Long?, note: NoteModel): Completable

    @DELETE("$NOTES_ENDPOINT/{id}")
    fun deleteNote(id: Long): Completable
}