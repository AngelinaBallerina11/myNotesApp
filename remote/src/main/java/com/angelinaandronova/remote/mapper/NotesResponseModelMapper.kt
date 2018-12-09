package com.angelinaandronova.remote.mapper

import com.angelinaandronova.data.model.NoteEntity
import com.angelinaandronova.remote.model.NoteModel


open class NotesResponseModelMapper : ModelMapper<NoteModel, NoteEntity> {
    override fun mapToModel(entity: NoteEntity): NoteModel {
        return NoteModel(entity.id, entity.title)
    }

    override fun mapFromModel(model: NoteModel): NoteEntity {
        return NoteEntity(model.id, model.title)
    }
}