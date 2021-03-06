package com.angelinaandronova.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelinaandronova.domain.interactor.notes.DeleteNoteUseCase
import com.angelinaandronova.domain.interactor.notes.GetNotesUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.presentation.mapper.NotesViewMapper
import com.angelinaandronova.presentation.model.NoteView
import com.angelinaandronova.presentation.state.Resource
import com.angelinaandronova.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class BrowseNotesViewModel @Inject constructor(
    private val getNotes: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val viewMapper: NotesViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<NoteView>>> = MutableLiveData()

    init {
        fetchNotes()
    }

    override fun onCleared() {
        getNotes.dispose()
        super.onCleared()
    }

    fun getNotes(): LiveData<Resource<List<NoteView>>> {
        return liveData
    }

    fun fetchNotes() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getNotes.execute(BrowseNotesSubscriber())
    }

    fun delete(position: Int) {
        val note = liveData.value?.data?.get(position)
        note?.let {
            deleteNoteUseCase.execute(
                object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        Log.i("delete note", "DONE")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("delete note", "error ${e.localizedMessage}")
                    }

                },
                DeleteNoteUseCase.Params.forNote(it.id!!)
            )
        }
    }

    inner class BrowseNotesSubscriber : DisposableObserver<List<Note>>() {
        override fun onNext(list: List<Note>) {
            liveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    list.map { viewMapper.mapToView(it) },
                    null
                )
            )
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }
}