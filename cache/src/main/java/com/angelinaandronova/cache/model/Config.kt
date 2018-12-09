package com.angelinaandronova.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.angelinaandronova.cache.db.Constants

@Entity(tableName = Constants.Tables.Config.TABLE_NAME)
data class Config(

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastCacheTime: Long
)
