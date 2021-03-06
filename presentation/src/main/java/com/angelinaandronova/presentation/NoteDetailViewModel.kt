package com.angelinaandronova.presentation

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelinaandronova.domain.interactor.notes.CreateNoteUseCase
import com.angelinaandronova.domain.interactor.notes.EditNoteUseCase
import com.angelinaandronova.domain.interactor.notes.GetNoteUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.presentation.mapper.NotesViewMapper
import com.angelinaandronova.presentation.model.NoteView
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class NoteDetailViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val viewMapper: NotesViewMapper
) : ViewModel() {

    val noteLiveData: MutableLiveData<NoteView> = MutableLiveData()

    fun setNoteTitle(title: String) {
        var note = noteLiveData.value
        if (note == null) {
            note = NoteView(id = null, title = title)
            noteLiveData.value = note
        }
    }

    fun saveNote() {
        noteLiveData.value?.let {
            createNoteUseCase.execute(
                SaveNoteSubscriber(),
                CreateNoteUseCase.Params.forNote(viewMapper.mapToDomain(it))
            )
        }
    }

    fun getNote(noteId: Long) {
        getNoteUseCase.execute(
            GetNoteSubscriber(),
            GetNoteUseCase.Params.forNote(noteId)
        )
    }

    inner class SaveNoteSubscriber : DisposableObserver<Long>() {
        override fun onComplete() {
            Log.i("CreateNote", "DONE")
        }

        override fun onNext(noteId: Long) {
            Log.i("CreateNote", "NEXT $noteId")
        }

        override fun onError(e: Throwable) {
            Log.i("CreateNote", "ERROR")
        }

    }

    inner class GetNoteSubscriber : DisposableObserver<Note>() {
        override fun onNext(note: Note) {
            Log.i("GetNote", "onNext ${note.title}")
            noteLiveData.value = viewMapper.mapToView(note)
        }

        override fun onComplete() {
            Log.i("GetNote", "DONE")
        }

        override fun onError(e: Throwable) {
            Log.i("GetNote", "ERROR")
        }

    }

}


