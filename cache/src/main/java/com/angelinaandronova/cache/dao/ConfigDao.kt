package com.angelinaandronova.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.angelinaandronova.cache.model.Config
import io.reactivex.Single

@Dao
interface ConfigDao {

    @Query("SELECT * FROM config")
    fun getConfig(): Single<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfig(config: Config)
}