package com.angelinaandronova.cache.dao

import androidx.room.*
import com.angelinaandronova.cache.model.CachedNote
import com.angelinaandronova.data.model.NoteEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface NotesDao {

    @Query("DELETE FROM notes")
    fun clearNotes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNotes(notes: List<CachedNote>)

    @Query("SELECT * FROM notes ORDER BY id")
    fun getNotes(): Observable<List<CachedNote>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: Long): Observable<CachedNote>

    @Update
    fun editNote(note: CachedNote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createNote(note: CachedNote): Long

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteNote(id: Long)

}