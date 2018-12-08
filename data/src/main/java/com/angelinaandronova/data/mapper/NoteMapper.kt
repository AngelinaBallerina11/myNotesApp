package com.angelinaandronova.data.mapper

import com.angelinaandronova.data.model.NoteEntity
import com.angelinaandronova.domain.model.Note
import javax.inject.Inject


open class NoteMapper @Inject constructor() : EntityMapper<NoteEntity, Note> {
    override fun mapFromEntity(entity: NoteEntity): Note {
        return Note(id = entity.id, title = entity.title)
    }

    override fun mapToEntity(domain: Note): NoteEntity {
        return NoteEntity(id = domain.id, title = domain.title)
    }
}