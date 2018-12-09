package com.angelinaandronova.mynotesapplication.domain.interactor

import com.angelinaandronova.domain.executor.PostExecutionThread
import com.angelinaandronova.domain.interactor.notes.EditNoteUseCase
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


class EditNoteUseCaseTest {

    private lateinit var editNoteUseCase: EditNoteUseCase
    @Mock
    lateinit var notesRepository: NotesRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        editNoteUseCase = EditNoteUseCase(notesRepository, postExecutionThread)
    }

    private fun stubEditNote(completable: Completable) {
        whenever(notesRepository.editNote(any())).thenReturn(completable)
    }

    @Test
    fun `editing a note completes`() {
        stubEditNote(Completable.complete())
        val testObserver = editNoteUseCase.getUseCaseCompletable(
            EditNoteUseCase.Params.forNote(NoteDataFactory.makeNote())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `edit note throws exception when no note is passed`() {
        editNoteUseCase.getUseCaseCompletable().test()
    }
}