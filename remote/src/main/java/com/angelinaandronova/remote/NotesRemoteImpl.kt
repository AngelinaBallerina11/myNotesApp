package com.angelinaandronova.remote

import com.angelinaandronova.data.model.NoteEntity
import com.angelinaandronova.data.repository.NotesRemote
import com.angelinaandronova.remote.mapper.NotesResponseModelMapper
import com.angelinaandronova.remote.service.NotesService
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject


class NotesRemoteImpl @Inject constructor(
    private val service: NotesService,
    private val mapper: NotesResponseModelMapper
) : NotesRemote {
    override fun getNotes(): Observable<List<NoteEntity>> {
        return service
            .getNotes()
            .map { list -> list.map { mapper.mapFromModel(it) } }
    }

    override fun getNote(id: Long): Observable<NoteEntity> {
        return service
            .retrieveNote(id)
            .map { mapper.mapFromModel(it) }
    }

    override fun editNote(note: NoteEntity): Observable<NoteEntity> {
        return service.updateNote(note.id, mapper.mapToModel(note))
            .map { mapper.mapFromModel(it) }
    }

    override fun createNote(note: NoteEntity): Observable<NoteEntity> {
        return service.createNote(mapper.mapToModel(note))
            .map { mapper.mapFromModel(it) }
    }

    override fun deleteNote(id: Long): Completable {
        return service.deleteNote(id)
    }
}