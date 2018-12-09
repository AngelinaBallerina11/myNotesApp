package com.angelinaandronova.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.angelinaandronova.cache.db.Constants.Tables.Notes.ID
import com.angelinaandronova.cache.db.Constants.Tables.Notes.TABLE_NAME
import com.angelinaandronova.cache.db.Constants.Tables.Notes.TITLE

@Entity(tableName = TABLE_NAME)
data class CachedNote(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Long? = null,

    @ColumnInfo(name = TITLE)
    val title: String
)