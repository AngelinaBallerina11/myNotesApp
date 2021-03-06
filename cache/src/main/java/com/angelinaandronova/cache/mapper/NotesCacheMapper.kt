package com.angelinaandronova.cache.mapper

import com.angelinaandronova.cache.model.CachedNote
import com.angelinaandronova.data.model.NoteEntity
import javax.inject.Inject


class NotesCacheMapper @Inject constructor() : CacheMapper<CachedNote, NoteEntity> {
    override fun mapFromCached(cached: CachedNote): NoteEntity {
        return NoteEntity(cached.id!!, cached.title)
    }

    override fun mapToCached(entity: NoteEntity): CachedNote {
        return CachedNote(entity.id, entity.title)
    }
}