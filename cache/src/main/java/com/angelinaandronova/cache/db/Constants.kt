package com.angelinaandronova.cache.db


object Constants {

    object Tables {

        object Notes {
            const val TABLE_NAME = "notes"
            const val ID = "id"
            const val TITLE = "title"
        }

        object Config {
            const val TABLE_NAME = "config"
            const val ID = "id"
            const val LAST_CACHED_TIME = "lastCacheTime"
        }

    }

    object Db {
        const val DB_NAME = "notes.db"
    }
}