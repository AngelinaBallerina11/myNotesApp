package com.angelinaandronova.mynotesapplication.domain.interactor

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.notes.DeleteNoteUseCase
import com.angelinaandronova.domain.interactor.notes.GetNoteUseCase
import com.angelinaandronova.domain.model.Note
import com.angelinaandronova.domain.repository.NotesRepository
import com.angelinaandronova.mynotesapplication.domain.utils.NoteDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class DeleteNoteUseCaseTest {

    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    @Mock
    lateinit var notesRepository: NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deleteNoteUseCase = DeleteNoteUseCase(notesRepository, postExecutionThread)
    }

    private fun stubGetNotes(completable: Completable) {
        whenever(notesRepository.deleteNote(any())).thenReturn(completable)
    }

    @Test
    fun `delete a note completes`() {
        stubGetNotes(Completable.complete())
        val testObserver = deleteNoteUseCase.getUseCaseCompletable(
            DeleteNoteUseCase.Params.forNote(NoteDataFactory.randomLong())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `delete note throws exception when no ID is passed`() {
        deleteNoteUseCase.getUseCaseCompletable().test()
    }
}